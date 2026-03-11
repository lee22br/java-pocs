package org.example;

public class User {

    private String name;

    @MinMaxRange(min = 18, max = 65, message = "Age must be between 18 and 65")
    private int age;

    public User(int age) {
        this.age = age;
    }
}
