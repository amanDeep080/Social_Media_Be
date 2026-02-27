package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepo;
import com.example.demo.utils.JwtUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {

        if (userRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (userRepo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        String hash = BCrypt.withDefaults()
                .hashToString(12, req.getPassword().toCharArray());

        User u = new User();
        u.setUsername(req.getUsername());
        u.setFullName(req.getFullName());
        u.setEmail(req.getEmail());
        u.setPassword(hash);

        // ✅ set default role at register time
        u.setRole(UserRole.USER);

        userRepo.save(u);

        String token = jwtUtil.generateToken(u.getEmail());
        return new AuthResponse(token, "Registered successfully");
    }

    public AuthResponse login(LoginRequest req) {

        User u = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        BCrypt.Result res = BCrypt.verifyer().verify(
                req.getPassword().toCharArray(),
                u.getPassword()
        );

        if (!res.verified) {
            throw new RuntimeException("Invalid email or password");
        }

        // ✅ (optional safety) if role null in old users, set it
        if (u.getRole() == null) {
            u.setRole(UserRole.USER);
            userRepo.save(u);
        }

        String token = jwtUtil.generateToken(u.getEmail());
        return new AuthResponse(token, "Login successful");
    }
}