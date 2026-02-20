package com.ink.vault.inkvault.controller;

import com.ink.vault.inkvault.model.User;
import com.ink.vault.inkvault.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUserController {

    private final UserRepository userRepository;

    public TestUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/test/create-user")
    public void createUser() {
        if (!userRepository.existsByEmail("test@inkvault.com")) {
            userRepository.save(new User("test@inkvault.com", "dummy-hash"));
        }
    }
}
