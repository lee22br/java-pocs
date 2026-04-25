package org.example.strategy;

public interface StorageStrategy {
    String uploadFile(String fileName, byte[] fileData);
}