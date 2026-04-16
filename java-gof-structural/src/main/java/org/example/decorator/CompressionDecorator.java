package org.example.decorator;

public class CompressionDecorator extends EvidenceProcessorDecorator {

    public CompressionDecorator(EvidenceProcessor evidenceProcessor) {
        super(evidenceProcessor);
    }

    @Override
    public String process(String data) {
        String previousLayerResult = super.process(data);
        return compress(previousLayerResult);
    }

    @Override
    public String restore(String processedData) {
        String decompressedData = decompress(processedData);
        return super.restore(decompressedData);
    }

    private String compress(String data) {
        // Mock compression by adding a prefix/suffix
        return "[COMPRESSED]" + data + "[/COMPRESSED]";
    }

    private String decompress(String data) {
        return data.replace("[COMPRESSED]", "").replace("[/COMPRESSED]", "");
    }
}
