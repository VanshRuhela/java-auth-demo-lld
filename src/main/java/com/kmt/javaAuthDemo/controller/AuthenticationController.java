package com.kmt.javaAuthDemo.controller;

import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.utils.security.TokenUtil;

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;

    }

    public String login(String username, String password) {
        System.out.println("Received login request for user: " + username);
        if (authenticationService.authenticate(username, password)) {
            String token = TokenUtil.generateToken(username);
            System.out.println("Login successful for user: " + username + ". Token: " + token + ", Expiry Time: " + TokenUtil.getExpiryDateTime(token));
            return token;
        } else {
            System.out.println("Login failed for user: " + username);
            return null;
        }
    }

    // Logout can be handled at client side by simply discarding the token
    private void logout(String sessionId) {}
}
