package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager() {
        // Dummy manager – Mockito overrides it in tests
        return new ProviderManager(Collections.emptyList());
    }
}
