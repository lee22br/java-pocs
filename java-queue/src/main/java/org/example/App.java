package org.example;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Queue<String> serviceQueue = new LinkedList<>();

        // offer(e): Inserts an element at the tail of the queue. Preferred over add()
        System.out.println("Adding customers to the queue:");
        serviceQueue.offer("Customer A - John");
        serviceQueue.offer("Customer B - Mary");
        serviceQueue.offer("Customer C - Peter");
        serviceQueue.offer("Customer D - Anna");

        System.out.println("Current Queue: " + serviceQueue);
        System.out.println("------------------------------------");

        // peek(): Retrieves the element at the head of the queue without removing it.
        String nextCustomer = serviceQueue.peek();
        System.out.println("Next to be served (peek): " + nextCustomer);
        System.out.println("Queue after peek: " + serviceQueue);
        System.out.println("------------------------------------");

        while (!serviceQueue.isEmpty()) {
            // poll(): Retrieves and removes the element at the head of the queue.
            System.out.println("Serving customers:");
            String servedCustomer1 = serviceQueue.poll();
            System.out.println("Served: " + servedCustomer1);
            System.out.println("Queue after serving: " + serviceQueue);
        }
        System.out.println("Queue empty: " + serviceQueue);

    }
}


