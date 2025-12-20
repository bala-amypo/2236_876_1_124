package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userAccountRepository.existsByEmail(email);
    }

    @Override
    public UserAccount saveUser(UserAccount user) {
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEmail(email).orElse(null);
    }
}
