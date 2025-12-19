package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil,
                          UserAccountService userAccountService,
                          PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount request) {

        UserAccount user = userAccountService.findByEmailOrThrow(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(Map.of(
                "token", token,
                "userId", user.getId(),
                "email", user.getEmail(),
                "role", user.getRole()
        ));
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccount request) {

        // Check if user exists
        if (userAccountService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        // Encode password
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // Set default role if missing
        if (request.getRole() == null) {
            request.setRole("USER");
        }

        UserAccount savedUser = userAccountService.register(request);

        String token = jwtUtil.generateToken(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

        return ResponseEntity.ok(Map.of(
                "token", token,
                "userId", savedUser.getId(),
                "email", savedUser.getEmail(),
                "role", savedUser.getRole()
        ));
    }
}
