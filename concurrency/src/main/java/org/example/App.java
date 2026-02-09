package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {
            System.out.println("Processing thread: " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return 42;
        };

        try {
            Future<Integer> futuro = executor.submit(task);
            
            System.out.println("Do something...");
            
            System.out.println("Task result: " + futuro.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
