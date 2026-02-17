package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
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
