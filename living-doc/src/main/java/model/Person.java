package model;

import ch.ifocusit.livingdoc.annotations.UbiquitousLanguage;

@UbiquitousLanguage(id = 200)
public class Person {
    private String name;
    private String email;
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void showInfo() {
        System.out.println("Name: " + name + ", Email: " + email);
    }
}