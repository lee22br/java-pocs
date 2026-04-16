package org.example.decorator;

import java.util.Base64;

public class EncryptionDecorator extends EvidenceProcessorDecorator {

    public EncryptionDecorator(EvidenceProcessor evidenceProcessor) {
        super(evidenceProcessor);
    }

    @Override
    public String process(String data) {
        // First, evidenceProcessor run job, then encrypt the result
        String previousLayerResult = super.process(data);
        return encode(previousLayerResult);
    }

    @Override
    public String restore(String processedData) {
        // First decrypt, then pass down to the inner layers
        String decryptedData = decode(processedData);
        return super.restore(decryptedData);
    }

    private String encode(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    private String decode(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}
