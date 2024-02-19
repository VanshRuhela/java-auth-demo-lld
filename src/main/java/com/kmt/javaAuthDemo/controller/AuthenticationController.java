package com.kmt.javaAuthDemo.controller;

import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.SessionService;

public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final SessionService sessionService;

    public AuthenticationController(AuthenticationService authenticationService, SessionService sessionService) {
        this.authenticationService = authenticationService;
        this.sessionService = sessionService;
    }

    public String login(String username, String password) {
        System.out.println("Received login request for user: " + username);
        if (authenticationService.authenticate(username, password)) {
            String sessionId = sessionService.createSession(username);
            System.out.println("Login successful for user: " + username + ". Session ID: " + sessionId);
            return sessionId;
        } else {
            System.out.println("Login failed for user: " + username);
            return null;
        }
    }

    public void logout(String sessionId) {
        System.out.println("Received logout request for session ID: " + sessionId);
        sessionService.endSession(sessionId);
        System.out.println("Logout successful for session ID: " + sessionId);
    }
}
