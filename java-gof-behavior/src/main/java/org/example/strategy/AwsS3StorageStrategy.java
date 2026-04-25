package org.example.strategy;

public class AwsS3StorageStrategy implements StorageStrategy {
    private final String bucketName;

    public AwsS3StorageStrategy(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public String uploadFile(String fileName, byte[] fileData) {
        // In a real scenario, AWS SDK code goes here
        System.out.println("Connecting to AWS S3 bucket: " + bucketName);
        System.out.println("Uploading " + fileData.length + " bytes...");
        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }
}
