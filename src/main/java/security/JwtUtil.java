package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private final byte[] secret;
    private final long expirationMs;

    // Constructor used in TESTS (t53–t55)
    public JwtUtil(byte[] secret, long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    // Constructor used by Spring (optional)
    public JwtUtil() {
        this.secret = "supplier-diversity-secret-key-1234567890"
                .getBytes(java.nio.charset.StandardCharsets.UTF_8);
        this.expirationMs = 3600000L;
    }

    // ================= GENERATE TOKEN =================
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // ================= EXTRACT CLAIMS =================
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    // ================= VALIDATE =================
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
