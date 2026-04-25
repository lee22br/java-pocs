package org.example.strategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUploaderServiceTest {

    private FileUploaderService uploaderService;
    private final byte[] dummyData = "Hello Strategy Pattern".getBytes();

    @BeforeEach
    void setUp() {
        uploaderService = new FileUploaderService(new LocalStorageStrategy("/var/tmp/uploads"));
    }

    @Test
    void testLocalStorageUpload() {
        String resultPath = uploaderService.upload("report.pdf", dummyData);

        assertEquals("file:///var/tmp/uploads/report.pdf", resultPath);
        assertTrue(resultPath.startsWith("file://"));
    }

    @Test
    void testDynamicStrategySwitchToAwsS3() {
        //strategy at runtime
        uploaderService.setStrategy(new AwsS3StorageStrategy("lnaix-prod-bucket"));

        String resultPath = uploaderService.upload("avatar.png", dummyData);

        assertEquals("https://lnaix-prod-bucket.s3.amazonaws.com/avatar.png", resultPath);
        assertTrue(resultPath.contains("s3.amazonaws.com"));
    }
}
