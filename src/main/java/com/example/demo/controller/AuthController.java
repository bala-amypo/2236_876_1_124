package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountService service,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        UserAccount user = new UserAccount(
                null,
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        UserAccount saved = service.register(user);

        String token = jwtUtil.generateToken(
                saved.getId(), saved.getEmail(), saved.getRole());

        return ResponseEntity.ok(
                new JwtResponse(token, saved.getId(), saved.getEmail(), saved.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException("Invalid credentials");
        }

        UserAccount user = service.findByEmailOrThrow(request.getEmail());

        String token = jwtUtil.generateToken(
                user.getId(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(
                new JwtResponse(token, user.getId(), user.getEmail(), user.getRole()));
    }
}
