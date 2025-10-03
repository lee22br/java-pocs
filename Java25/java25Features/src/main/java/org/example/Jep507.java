package org.example;

public class Jep507 {

    public static void main (String[] args){
        System.out.println("Test Primitive Types Patterns");

        Object obj = 28;

        if (obj instanceof int i) {
            System.out.println("This Object is int: "+i);
        }

        obj = 3.1475;

        switch (obj) {
            case int i -> System.out.println("int: "+i);
            case double d -> System.out.println("double: "+d);
            case boolean b -> System.out.println("boolean: "+b);
            default -> System.out.println("Other Type: ");
        }
    }
}
