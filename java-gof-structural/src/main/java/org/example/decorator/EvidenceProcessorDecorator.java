package org.example.decorator;

public abstract class EvidenceProcessorDecorator implements EvidenceProcessor {
    protected EvidenceProcessor evidenceProcessor;

    public EvidenceProcessorDecorator(EvidenceProcessor evidenceProcessor) {
        this.evidenceProcessor = evidenceProcessor;
    }

    @Override
    public String process(String data) {
        return evidenceProcessor.process(data);
    }

    @Override
    public String restore(String processedData) {
        return evidenceProcessor.restore(processedData);
    }

     static void main(String[] args) {
        String rawData = IO.readln("Enter the data to be processed: ");

        EvidenceProcessor pipeline = new EncryptionDecorator(
                new CompressionDecorator(
                        new BaseEvidenceProcessor()
                )
        );
        String processed = pipeline.process(rawData);
        IO.println("Processed data: "+ processed);

        String restored = pipeline.restore(processed);
        IO.println("Restored process: "+restored);
    }
}
