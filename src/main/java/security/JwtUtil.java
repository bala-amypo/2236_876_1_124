package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // ⚠️ Demo secret — OK for assignment/testing
    private static final String SECRET_KEY = "demo-secret-key-123456";

    // 1 hour
    private static final long EXPIRATION_TIME = 3600000L;

    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                .setClaims(Map.of(
                        "userId", userId,
                        "role", role
                ))
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
