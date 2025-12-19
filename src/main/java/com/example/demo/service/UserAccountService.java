package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    UserAccount register(UserAccount user);

    UserAccount findByEmailOrThrow(String email);

    boolean existsByEmail(String email);

    UserAccount findByEmail(String email);
}
