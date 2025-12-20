package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
    boolean existsByEmail(String email);
    UserAccount saveUser(UserAccount user);
    UserAccount findByEmail(String email);
}
