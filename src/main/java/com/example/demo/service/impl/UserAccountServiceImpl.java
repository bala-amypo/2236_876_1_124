package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // ✅ This tells Spring to manage this class as a bean
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repo;
    private final PasswordEncoder passwordEncoder;

    // Spring will automatically inject these beans
    public UserAccountServiceImpl(UserAccountRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount register(UserAccount user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public UserAccount findByEmailOrThrow(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
