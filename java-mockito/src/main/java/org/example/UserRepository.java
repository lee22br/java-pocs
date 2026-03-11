package org.example;

import java.util.Optional;

public class UserRepository {

    public Optional<User> findById (Long id){
        return Optional.empty();
    }

    public void save (User user){
        System.out.println("Saving user in DB...");
    }
}
