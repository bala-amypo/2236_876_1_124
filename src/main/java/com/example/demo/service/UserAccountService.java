package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    UserAccount save(UserAccount user);

    UserAccount findByEmail(String email);   // ✅ ADD THIS
}
