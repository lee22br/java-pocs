package org.example;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    void testAtomicIncrement() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int numberOfThreads = 1000;
        ExecutorService service = Executors.newFixedThreadPool(10);
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
}
