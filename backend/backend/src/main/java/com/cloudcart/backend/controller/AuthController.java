package com.cloudcart.backend.controller;

import com.cloudcart.backend.dto.AuthResponseDTO;
import com.cloudcart.backend.dto.LoginRequest;
import com.cloudcart.backend.entity.User;

import com.cloudcart.backend.security.JwtUtil;
import com.cloudcart.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.cloudcart.backend.dto.RegisterRequestDTO;
import jakarta.validation.Valid;
import com.cloudcart.backend.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setRole("USER");

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN
    @PostMapping("/login")

    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest request) {

        User existingUser = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found")
                );

        if (passwordEncoder.matches(
                request.getPassword(),
                existingUser.getPassword())) {

            String token = jwtUtil.generateToken(
                    existingUser.getEmail(),
                    existingUser.getRole()
            );

            return ResponseEntity.ok(
                    new AuthResponseDTO(
                            token,
                            existingUser.getRole())
            );
        }

        return ResponseEntity
                .badRequest()
                .body("Invalid Credentials");
    }
}