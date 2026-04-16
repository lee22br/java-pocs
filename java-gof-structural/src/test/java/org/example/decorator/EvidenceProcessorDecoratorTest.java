package org.example.decorator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EvidenceProcessorDecoratorTest {

    @Test
    public void testBaseProcessorOnly() {
        String rawData = "device_memory_dump_0x4A";

        EvidenceProcessor processor = new BaseEvidenceProcessor();

        String processed = processor.process(rawData);
        assertEquals(rawData, processed, "Base processor should not alter data.");
    }

    @Test
    public void testEncryptionDecorator() {
        String rawData = "suspect_chat_logs";

        // Encryption
        EvidenceProcessor encryptedProcessor = new EncryptionDecorator(new BaseEvidenceProcessor());

        String processed = encryptedProcessor.process(rawData);
        assertNotEquals(rawData, processed);

        String restored = encryptedProcessor.restore(processed);
        assertEquals(rawData, restored, "Decrypted data must match original raw data.");
    }

    @Test
    public void testStackedDecorators_CompressThenEncrypt() {
        String rawData = "large_image_hash_table";

        //stacking decorators
        EvidenceProcessor pipeline = new EncryptionDecorator(
                new CompressionDecorator(
                        new BaseEvidenceProcessor()
                )
        );

        String processed = pipeline.process(rawData);

        assertNotEquals(rawData, processed);

        String restored = pipeline.restore(processed);
        assertEquals(rawData, restored, "Data restored through the full pipeline must match the original.");
    }
}