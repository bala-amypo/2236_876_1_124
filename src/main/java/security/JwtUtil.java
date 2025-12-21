package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    // Constructor used in tests
    public JwtUtil(byte[] secret, long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret);
        this.expirationMs = expirationMs;
    }

    // Default constructor for Spring
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(
                "supplier-diversity-secret-key-1234567890"
                        .getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );
        this.expirationMs = 3600000L;
    }

    // ---------------- TOKEN GENERATION ----------------
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ---------------- EXTRACTION METHODS ----------------
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public Long extractUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // 🔧 Compatibility for JwtAuthenticationFilter
    public String getUsernameFromToken(String token) {
        return extractEmail(token);
    }

    // ---------------- VALIDATION ----------------
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
