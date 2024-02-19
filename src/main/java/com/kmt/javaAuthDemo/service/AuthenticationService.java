package com.kmt.javaAuthDemo.service;

import com.kmt.javaAuthDemo.model.User;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.utils.security.PasswordHashingUtil;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        String hashedPassword = PasswordHashingUtil.hashPassword(password);
        if (user != null && user.getPassword().equals(hashedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
