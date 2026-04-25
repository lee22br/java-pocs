package org.example.strategy;

public class LocalStorageStrategy implements StorageStrategy {
    private final String localDirectory;

    public LocalStorageStrategy(String localDirectory) {
        this.localDirectory = localDirectory;
    }

    @Override
    public String uploadFile(String fileName, byte[] fileData) {
        System.out.println("Writing file to local disk at: " + localDirectory);
        return "file://" + localDirectory + "/" + fileName;
    }
}