package org.example;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public void updateUsername (Long id, String userName) {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setName(userName);
                userRepository.save(user);
                emailService.sendEmail("test@example.com","updated");
            }
    }
}
