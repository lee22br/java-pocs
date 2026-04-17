package org.example.facade;

//Metadata Extraction (filesystem data)
public interface MetadataExtractor {
    String extractMetadata(byte[] rawData);
}