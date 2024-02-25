package com.kmt.javaAuthDemo.service;

import com.kmt.javaAuthDemo.model.User;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.utils.security.PasswordHashingUtil;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        System.out.println("Attempting to authenticate user: " + username);
        User user = userRepository.getUserByUsername(username);
        String hashedPassword = PasswordHashingUtil.hashPassword(password);
        if (user != null && user.getPassword().equals(hashedPassword)) {
            System.out.println("Authentication successful for user: " + username);
            return user;
        } else {
            System.out.println("Authentication failed for user: " + username);
            return null;
        }
    }
}
