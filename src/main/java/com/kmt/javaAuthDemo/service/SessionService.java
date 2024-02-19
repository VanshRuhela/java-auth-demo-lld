package com.kmt.javaAuthDemo.service;

import com.kmt.javaAuthDemo.repository.SessionRepository;

import java.util.UUID;

public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        sessionRepository.saveSession(sessionId, username);
        return sessionId;
    }

    public boolean isValidSession(String sessionId) {
        return sessionRepository.getUsernameBySessionId(sessionId) != null;
    }

    public void endSession(String sessionId) {
        sessionRepository.deleteSession(sessionId);
    }
}
