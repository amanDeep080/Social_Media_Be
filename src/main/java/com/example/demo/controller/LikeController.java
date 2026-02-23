package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.example.demo.model.Like;
import com.example.demo.service.LikeService;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Like> likePost(@Valid @RequestBody Like like) {
        Like savedLike = likeService.likePost(like);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dislikePost(@PathVariable("id") Long id) {
        likeService.dislikePost(id);
        return ResponseEntity.noContent().build();
    }
}