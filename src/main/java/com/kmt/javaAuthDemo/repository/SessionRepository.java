package com.kmt.javaAuthDemo.repository;

import java.util.HashMap;
import java.util.Map;

public class SessionRepository {
    private final Map<String, String> sessions = new HashMap<>();

    public void saveSession(String sessionId, String username) {
        sessions.put(sessionId, username); 
    }

    public String getUsernameBySessionId(String sessionId) {
        return sessions.get(sessionId);
    }

    public void deleteSession(String sessionId) {
        sessions.remove(sessionId);
    }
}
