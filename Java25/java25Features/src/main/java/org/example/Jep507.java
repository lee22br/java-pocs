package org.example;

public class Jep507 {

    public static void main (String[] args){
        System.out.println("Test Primitive Types Patterns");

        Object obj = 28;

        if (obj instanceof int i) {
            System.out.println("This Object is int: "+i);
        }
    }
}
