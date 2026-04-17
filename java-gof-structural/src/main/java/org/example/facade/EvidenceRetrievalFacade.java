package org.example.facade;

public class EvidenceRetrievalFacade {

    // 3 systems to process
    private final CloudStorageService storageService;
    private final MetadataExtractor metadataExtractor;
    private final AuditLogger auditLogger;

    private final String evidenceBucket;

    public EvidenceRetrievalFacade(CloudStorageService storageService, MetadataExtractor metadataExtractor, AuditLogger auditLogger, String evidenceBucket) {
        this.storageService = storageService;
        this.metadataExtractor = metadataExtractor;
        this.auditLogger = auditLogger;
        this.evidenceBucket = evidenceBucket;
    }

    /**
     * A Simple method to get all data and log your access from 3 different systems
     */
    public ForensicEvidence retrieveAndAuditEvidence(String caseId, String evidenceId, String investigatorId) {
        // 1. Fetch raw data
        String objectKey = caseId + "/" + evidenceId;
        byte[] rawData = storageService.downloadBlob(evidenceBucket, objectKey);

        // 2. Extract metadata
        String metadata = metadataExtractor.extractMetadata(rawData);

        // 3. Log chain of custody
        auditLogger.logAccess(caseId, evidenceId, "EVIDENCE_RETRIEVED", investigatorId);

        // 4. Return result
        return new ForensicEvidence(caseId, evidenceId, rawData, metadata);
    }
}
