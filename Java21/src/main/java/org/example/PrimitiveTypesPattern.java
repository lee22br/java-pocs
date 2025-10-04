package org.example;

public class PrimitiveTypesPattern {

    public static void main (String[] args){
        System.out.println("Test Primitive Types Patterns");

        Object obj = 28;

        if (obj instanceof Integer i) {
            System.out.println("This Object is int: "+i);
        }

        switch (obj) {
            case Integer i -> System.out.println("int: "+i);
            case Double d -> System.out.println("double: "+d);
            //default -> System.out.println("Other Type: ");
        }
    }
}
