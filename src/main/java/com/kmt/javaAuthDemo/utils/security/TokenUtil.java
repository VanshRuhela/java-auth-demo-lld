package com.kmt.javaAuthDemo.utils.security;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class TokenUtil {
    private static final long EXPIRY_TIME = 1000 * 60 * 60; //1 hour

    public static String generateToken(String username) {
        long expiryTime = System.currentTimeMillis() + (1000 * 60 * 60); // 1 hour expiry
        String tokenData = username + ":" + expiryTime + ":" + UUID.randomUUID();
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
            if (parts.length != 3) {
                return false;
            }
            long expiryTime = Long.parseLong(parts[1]);
            return System.currentTimeMillis() < expiryTime;
        } catch (IllegalArgumentException e) {
            return false; // Invalid Base64 encoding
        }
    }

    public static String getUsernameFromToken(String token) {
        String decodedToken = new String(Base64.getDecoder().decode(token));
        return decodedToken.split(":")[0];
    }

    public static Date getExpiryDateTime(String encodedToken) {
        String tokenData = decodeToken(encodedToken);
        String[] parts = tokenData.split(":");
        if (parts.length >= 3) {
            long expiryTimeMillis = Long.parseLong(parts[1]);
            return new Date(expiryTimeMillis);
        }
        return null; // Invalid token format
    }
}

