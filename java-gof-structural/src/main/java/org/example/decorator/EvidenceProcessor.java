package org.example.decorator;

public interface EvidenceProcessor {
    /**
     * Processes the raw evidence
     */
    String process(String data);

    /**
     * Reverts the processing
     */
    String restore(String processedData);
}
