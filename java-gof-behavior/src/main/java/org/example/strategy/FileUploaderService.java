package org.example.strategy;

public class FileUploaderService {

    private StorageStrategy strategy;

    // The strategy injected via constructor
    public FileUploaderService(StorageStrategy strategy) {
        this.strategy = strategy;
    }

    // dynamically at runtime
    public void setStrategy(StorageStrategy strategy) {
        this.strategy = strategy;
    }

    public String upload(String fileName, byte[] fileData) {
        if (strategy == null) {
            throw new IllegalStateException("Storage strategy is not set!");
        }
        // Delegates to the Strategy object
        return strategy.uploadFile(fileName, fileData);
    }
}
