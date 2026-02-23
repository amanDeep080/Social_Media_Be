package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostByUserId(
            @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable("id") Long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }
}