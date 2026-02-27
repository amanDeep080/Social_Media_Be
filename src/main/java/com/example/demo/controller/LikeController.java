package com.example.demo.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ToggleResponse;
import com.example.demo.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class LikeController {

    private final LikeService likeService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{postId}/like")
    public ResponseEntity<ToggleResponse> toggleLike(
            @PathVariable("postId") Long postId,
            Principal principal) {

        boolean liked = likeService.toggleLike(postId, principal.getName());
        return ResponseEntity.ok(new ToggleResponse(liked));
    }
}