package org.example;

public class Jep507 {

    public static void main (String[] args) {
        System.out.println("Test Primitive Types Patterns");

        Object obj = 28;

        if (obj instanceof int i) {
            System.out.println("This Object is int: " + i);
        }

        obj = 3.1475;

        switch (obj) {
            case int i -> System.out.println("int: " + i);
            case double d -> System.out.println("double: " + d);
            case boolean b -> System.out.println("boolean: " + b);
            default -> System.out.println("Other Type: ");
        }

        switch (getStatus()) {
            case 0 -> System.out.println("OK");
            case 1 -> System.out.println("WARN");
            case 2 -> System.out.println("ERRO");
            case int i when i >= 10 -> System.out.println("Fatal Error: "+i);
            case int i -> System.out.println("Unknown Status: "+i);
        }
    }

    public static int getStatus() {
        return 8;
    }
}
