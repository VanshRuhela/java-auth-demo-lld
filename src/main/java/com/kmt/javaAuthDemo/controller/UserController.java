package com.kmt.javaAuthDemo.controller;

import com.kmt.javaAuthDemo.model.Role;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.UserService;
import com.kmt.javaAuthDemo.utils.security.TokenUtil;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void registerAdmin(String token, String username, String password) {
        System.out.println("Received request to register admin");
        if (TokenUtil.validateToken(token)) {
            Role requestorRole = TokenUtil.getRoleFromToken(token);
            if (requestorRole == Role.ADMIN) {
                userService.registerAdmin(username, password);
                System.out.println("Admin registered successfully with username: " + username);
            } else {
                System.out.println("Unauthorized: Only admins can register new admins.");
            }
        } else {
            System.out.println("Authentication failed. Admin registration failed.");
        }
    }

    public void registerLibrarian(String token, String username, String password) {
        System.out.println("Received request to register librarian");
        if (TokenUtil.validateToken(token)) {
            Role requestorRole = TokenUtil.getRoleFromToken(token);
            if (requestorRole == Role.ADMIN) {
                userService.registerLibrarian(username, password);
                System.out.println("Librarian registered successfully with username: " + username);
            } else {
                System.out.println("Unauthorized: Only admins can register new librarians.");
            }
        } else {
            System.out.println("Authentication failed. Librarian registration failed.");
        }
    }

    public void registerCustomer(String token, String username, String password) {
        System.out.println("Received request to register customer");
        if (TokenUtil.validateToken(token)) {
            Role requestorRole = TokenUtil.getRoleFromToken(token);
            if (requestorRole == Role.ADMIN || requestorRole == Role.LIBRARIAN) {
                userService.registerCustomer(username, password);
                System.out.println("Customer registered successfully with username: " + username);
            } else {
                System.out.println("Unauthorized: Only admins and librarians can register new customer.");
            }
        } else {
            System.out.println("Authentication failed. Customer registration failed.");
        }
    }
}
