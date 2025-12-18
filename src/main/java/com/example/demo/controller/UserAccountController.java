package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    // REGISTER USER
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return service.save(user); // ✅ FIX
    }

    // SIMPLE FETCH (OPTIONAL)
    @GetMapping("/by-email")
    public UserAccount getByEmail(@RequestParam String email) {
        return service.findByEmail(email); // ✅ FIX
    }
}
