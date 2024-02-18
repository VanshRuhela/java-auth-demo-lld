package com.kmt.javaAuthDemo.service;

import com.kmt.javaAuthDemo.model.User;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.utils.security.PasswordHashingUtil;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password) {
        String hashedPassword = PasswordHashingUtil.hashPassword(password);
        User user = new User(username, hashedPassword);
        userRepository.addUser(user);
    }
}
