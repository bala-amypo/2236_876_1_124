package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserAccount;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountService userService,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public UserAccount register(@RequestBody RegisterRequest request) {

        UserAccount user = new UserAccount();
        user.setName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setRole(request.role); // defaults handled in entity

        return userService.register(user);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {

        UserAccount user =
                userService.login(request.email, request.password);

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        JwtResponse response = new JwtResponse();
        response.token = token;
        response.userId = user.getId();
        response.email = user.getEmail();
        response.role = user.getRole();

        return response;
    }
}
