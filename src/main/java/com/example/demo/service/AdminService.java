package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepo userRepo;

    @Transactional
    public User updateRole(Long userId, UserRole role) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        u.setRole(role);
        return userRepo.save(u);
    }
}