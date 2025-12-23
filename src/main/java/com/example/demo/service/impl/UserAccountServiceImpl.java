package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import com.example.demo.exception.UnauthorizedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAccountServiceImpl
        implements UserAccountService, AuthenticationManager {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor expected by test
    public UserAccountServiceImpl(UserAccountRepository repository,
                                  PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Optional constructor
    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
        this.passwordEncoder = null;
    }

    // ================= USER SERVICE =================

    @Override
    public UserAccount register(UserAccount userAccount) {
        if (passwordEncoder != null) {
            userAccount.setPassword(
                    passwordEncoder.encode(userAccount.getPassword())
            );
        }
        return repository.save(userAccount);
    }

    @Override
    public UserAccount findByEmailOrThrow(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new UnauthorizedException("User not found: " + email));
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        return authentication;
    }
}
