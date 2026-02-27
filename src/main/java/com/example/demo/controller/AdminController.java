package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RoleUpdateRequest;
import com.example.demo.model.User;
import com.example.demo.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // Only ADMIN can change roles
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<User> updateRole(@PathVariable Long userId, @RequestBody RoleUpdateRequest req) {
        User updated = adminService.updateRole(userId, req.getRole());
        return ResponseEntity.ok(updated);
    }
}