package com.kmt.javaAuthDemo.utils.security;

import com.kmt.javaAuthDemo.model.Role;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class TokenUtil {
    private static final long EXPIRY_TIME = 1000 * 60 * 60; //1 hour

    public static String generateToken(String username, String role) {
        long expiryTime = System.currentTimeMillis() + (1000 * 60 * 60); // 1 hour expiry
        String tokenData = username + ":" + role + ":" + expiryTime + ":" + UUID.randomUUID();
        return encodeToken(tokenData);
    }

    private static String encodeToken(String tokenData) {
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }

    private static String decodeToken(String encodedToken) {
        return new String(Base64.getDecoder().decode(encodedToken));
    }

    public static boolean validateToken(String token) {
        try {
            String decodedToken = decodeToken(token);
            String[] parts = decodedToken.split(":");
            if (parts.length != 4) {
                return false;
            }
            long expiryTime = Long.parseLong(parts[2]);
            return System.currentTimeMillis() < expiryTime;
        } catch (IllegalArgumentException e) {
            return false; // Invalid Base64 encoding
        }
    }

    public static String getUsernameFromToken(String token) {
        String decodedToken = decodeToken(token);
        String[] parts = decodedToken.split(":");
        return parts[0];
    }

    public static Date getExpiryDateTime(String token) {
        String decodedToken = decodeToken(token);
        String[] parts = decodedToken.split(":");
        long expiryTimeMillis = Long.parseLong(parts[2]);
        return new Date(expiryTimeMillis);

    }

    public static Role getRoleFromToken(String token) {
        String decodedToken = decodeToken(token);
        String[] parts = decodedToken.split(":");
        return Role.valueOf(parts[1]);
    }
}

