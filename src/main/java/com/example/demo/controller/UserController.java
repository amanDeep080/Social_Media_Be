package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserMiniResponse;
import com.example.demo.model.User;
import com.example.demo.repository.FollowRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepo userRepo;
    private final FollowRepo followRepo;
    private final FollowService followService; // ✅ ADD THIS

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<UserMiniResponse>> listUsers(Principal principal) {

        User me = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<UserMiniResponse> users = userRepo.findAll().stream()
                .filter(u -> !u.getId().equals(me.getId())) // ✅ FILTER BY ID
                .map(u -> new UserMiniResponse(u.getId(), u.getUsername(), u.getFullName(), u.getEmail()))
                .toList();

        return ResponseEntity.ok(users);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{userId}/follow")
    public ResponseEntity<Map<String, Boolean>> toggleFollow(
            @PathVariable("userId") Long userId,
            Principal principal) {

        User me = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (me.getId().equals(userId)) {
            throw new RuntimeException("You cannot follow yourself");
        }

        boolean active = followService.toggleFollow(me.getId(), userId);

        return ResponseEntity.ok(Map.of("active", active));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{userId}/is-following")
    public ResponseEntity<Map<String, Boolean>> isFollowing(
            @PathVariable("userId") Long userId,
            Principal principal) {

        User me = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean active = followRepo.existsByFollowerIdAndFollowingId(me.getId(), userId);
        return ResponseEntity.ok(Map.of("active", active));
    }
}