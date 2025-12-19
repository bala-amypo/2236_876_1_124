package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserAccountService userAccountService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        // Create UserAccount entity from DTO
        UserAccount user = new UserAccount();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user
        UserAccount savedUser = userAccountService.register(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());

        // Return response
        JwtResponse response = new JwtResponse();
        response.setToken(token);
        response.setUserId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());

        return ResponseEntity.ok(response);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Load user
            UserAccount user = userAccountService.findByEmailOrThrow(request.getEmail());

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

            // Return response
            JwtResponse response = new JwtResponse();
            response.setToken(token);
            response.setUserId(user.getId());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());

            return ResponseEntity.ok(response);

        } catch (AuthenticationException ex) {
            throw new UnauthorizedException("Invalid email or password");
        }
    }
}
