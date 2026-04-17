package org.example.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EvidenceRetrievalFacadeTest {

    @Mock
    private CloudStorageService mockStorageService;

    @Mock
    private MetadataExtractor mockMetadataExtractor;

    @Mock
    private AuditLogger mockAuditLogger;

    private EvidenceRetrievalFacade facade;

    private final String testBucket = "secure-forensics-bucket";

    @BeforeEach
    void setUp() {
        // mocks
        facade = new EvidenceRetrievalFacade(
                mockStorageService,
                mockMetadataExtractor,
                mockAuditLogger,
                testBucket
        );
    }

    @Test
    void testRetrieveAndAuditEvidence_Success() {
        String caseId = "2026-001";
        String evidenceId = "CEL-01";
        String investigatorId = "789";
        String expectedObjectKey = caseId + "/" + evidenceId;

        byte[] mockRawData = new byte[]{0x4A, 0x50, 0x45, 0x47};
        String mockMetadata = "CreationDate: 2026-04-15; Device: Iphone 11";

        when(mockStorageService.downloadBlob(testBucket, expectedObjectKey)).thenReturn(mockRawData);
        when(mockMetadataExtractor.extractMetadata(mockRawData)).thenReturn(mockMetadata);

        ForensicEvidence result = facade.retrieveAndAuditEvidence(caseId, evidenceId, investigatorId);

        assertNotNull(result);
        assertEquals(caseId, result.getCaseId());
        assertEquals(evidenceId, result.getEvidenceId());
        assertArrayEquals(mockRawData, result.getRawData());
        assertEquals(mockMetadata, result.getMetadata());

        // verify all phases was executed
        verify(mockStorageService).downloadBlob(testBucket, expectedObjectKey);
        verify(mockMetadataExtractor).extractMetadata(mockRawData);
        verify(mockAuditLogger).logAccess(caseId, evidenceId, "EVIDENCE_RETRIEVED", investigatorId);
    }
}
