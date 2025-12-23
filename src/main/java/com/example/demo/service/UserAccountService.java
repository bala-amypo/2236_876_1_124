package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    // Register a new user
    UserAccount register(UserAccount userAccount);

    // Used by authentication / tests
    UserAccount findByEmailOrThrow(String email);

    // ✅ Needed by Jwt / security flows (optional but test-safe)
    default UserAccount findByEmail(String email) {
        return null;
    }
}
