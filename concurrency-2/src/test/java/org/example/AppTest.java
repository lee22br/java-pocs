package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest{

    @Test
    void testCallableAndFuture() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Task Completed Successfully";
        };

        Future<String> future = executor.submit(task);

        System.out.println("Doing other work while waiting...");

        assertFalse(future.isDone(), "Future should not be done immediately");

        String result = future.get();

        assertEquals("Task Completed Successfully", result);
        assertTrue(future.isDone(), "Future should be done now");
        executor.shutdown();
    }
}
