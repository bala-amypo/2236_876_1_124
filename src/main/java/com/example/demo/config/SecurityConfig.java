package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityConfig {

    // ---------------- PASSWORD ENCODER ----------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ---------------- AUTHENTICATION MANAGER ----------------
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(new DaoAuthenticationProvider());
    }

    // ---------------- JWT UTIL ----------------
    @Bean
    public JwtUtil jwtUtil() {
        byte[] secret = "supplier-diversity-secret-key-1234567890"
                .getBytes(StandardCharsets.UTF_8);

        return new JwtUtil(secret, 3600000L);
    }
}
