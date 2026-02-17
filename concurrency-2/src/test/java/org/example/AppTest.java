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

        // 2. Define the task using Callable (returns a String after 1 second)
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Task Completed Successfully";
        };

        // 3. Submit the task and get a Future object
        Future<String> future = executor.submit(task);

        // Logic while the task runs in the background
        System.out.println("Doing other work while waiting...");

        // 4. Validate the status
        assertFalse(future.isDone(), "Future should not be done immediately");

        // 5. Retrieve the result (This call blocks until the result is ready)
        String result = future.get();

        // 6. Assertions
        assertEquals("Task Completed Successfully", result);
        assertTrue(future.isDone(), "Future should be done now");

        // Cleanup
        executor.shutdown();
    }
}
