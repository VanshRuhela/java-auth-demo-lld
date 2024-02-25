package com.kmt.javaAuthDemo.service;

import com.kmt.javaAuthDemo.model.Role;
import com.kmt.javaAuthDemo.model.User;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.utils.security.PasswordHashingUtil;
import com.kmt.javaAuthDemo.utils.security.TokenUtil;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void registerUser(String username, String password, Role role) {
        String hashedPassword = PasswordHashingUtil.hashPassword(password);
        User user = new User(username, hashedPassword, role);
        userRepository.addUser(user);
    }

    public void registerAdmin(String username, String password) {
        registerUser(username, password, Role.ADMIN);
    }

    public void registerLibrarian(String username, String password) {
        registerUser(username, password, Role.LIBRARIAN);
    }

    public void registerCustomer(String username, String password) {
        registerUser(username, password, Role.CUSTOMER);
    }
}
