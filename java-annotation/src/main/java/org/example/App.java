package org.example;

import static org.example.Validator.validate;

/**
 * Hello world!
 *
 */
public class App 
{
    static void main( String[] args ) throws IllegalAccessException {
        IO.println("My first Annotation...");
        User invalidUser = new User(15);
        User validUser = new User(30);
        User oldUser = new User(70);

        IO.println("\nChecking old User:");
        validate(oldUser);

        IO.println("Checking Invalid User:");
        validate(invalidUser);

        IO.println("\nChecking Valid User:");
        validate(validUser);


    }
}
