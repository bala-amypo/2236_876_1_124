package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtUtil jwtUtil() {
        // secret must be at least 32 bytes
        byte[] secret = "my-secret-key-my-secret-key-123".getBytes();
        Long expirationMs = 86400000L; // 1 day

        return new JwtUtil(secret, expirationMs);
    }
}
