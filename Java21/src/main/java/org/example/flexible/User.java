package org.example.flexible;

public class User extends Person {

    private String login;

    public User (String login, String name ) {
        super(name);
        if (null == login || login.isBlank()) {
            throw new IllegalArgumentException("Invalid login");
        }
        //super(name); //Error
        this.login = login;
    }
}
