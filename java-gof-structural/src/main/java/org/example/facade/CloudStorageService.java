package org.example.facade;

//Cloud Storage (AWS S3 abstraction)
public interface CloudStorageService {
    byte[] downloadBlob(String bucketName, String objectKey);
}
