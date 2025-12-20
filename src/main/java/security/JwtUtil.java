package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final byte[] secret;
    private final long expirationMs;

    public JwtUtil() {
        this.secret = "supplier-diversity-secret-key-1234567890".getBytes();
        this.expirationMs = 3600000L; // 1 hour
    }

    public JwtUtil(byte[] secret, long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    // Generate JWT using userId, email, role
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Long extractUserId(String token) {
        return ((Number) extractAllClaims(token).get("userId")).longValue();
    }

    public String extractEmail(String token) {
        return (String) extractAllClaims(token).get("email");
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null && claims.getExpiration().after(new Date());
    }
}
