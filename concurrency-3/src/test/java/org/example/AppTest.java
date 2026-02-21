package org.example;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private int sharedResource = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Map<String, String> syncCache = new HashMap<>();



    @Test
    void testAtomicIncrement() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int numberOfThreads = 1000;
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                counter.incrementAndGet(); // Thread-safe without synchronized
                latch.countDown();
            });
        }
        latch.await(); // Wait for all threads
        assertEquals(1000, counter.get(), "Counter should be exactly 1000");
    }


    @Test
    void testLockProtection() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1000; i++) {
            service.submit(() -> {
                lock.lock();
                try {
                    sharedResource++;
                } finally {
                    lock.unlock();
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        assertEquals(1000, sharedResource);
    }

    @Test
    void testReadWriteConcurrency() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        syncCache.put("key1", "Initial value");

        // One Writer: Holds the lock for a bit
        executor.submit(() -> {
            rwLock.writeLock().lock();
            try {
                System.out.println("Writer: Updating data...");
                Thread.sleep(800);
                syncCache.put("key1", "SafeValue");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                rwLock.writeLock().unlock();
                System.out.println("Writer: Released lock.");
            }
        });

        // Many Readers: Will try read
        for (int i = 0; i < 10; i++) {
            int readerId = i;
            executor.submit(() -> {
                rwLock.readLock().lock();
                try {
                    String val = syncCache.get("key1");
                    System.out.println("Reader " + readerId + " saw: " + val);
                    assertEquals("SafeValue", val, "Reader should see the consistent updated value");
                } finally {
                    rwLock.readLock().unlock();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

}
