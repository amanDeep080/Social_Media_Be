package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CreatePostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.model.Post;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.CommentService;
import com.example.demo.service.LikeService;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserRepo userRepo;
    private final LikeService likeService;
    private final CommentService commentService;

    private PostResponse toDto(Post p, String viewerEmail) {
        long likeCount = likeService.likeCount(p.getId());
        long commentCount = commentService.commentCount(p.getId());

        boolean likedByMe = false;

        if (viewerEmail != null) {
            var viewer = userRepo.findByEmail(viewerEmail).orElse(null);
            if (viewer != null) {
                likedByMe = likeService.likedByMe(p.getId(), viewer.getId());
            }
        }

        return new PostResponse(
                p.getId(),
                p.getContent(),
                p.getImageUrl(),
                p.getUser().getId(),
                p.getUser().getUsername(),
                p.getUser().getFullName(),
                p.getUser().getEmail(),
                likeCount,
                commentCount,
                likedByMe
        );
    }

    // ✅ Create post -> returns created post (with likedByMe=false)
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest req, Principal principal) {
        String email = principal.getName();
        Post created = postService.createPost(req, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created, email));
    }

    // ✅ Public feed (no viewer) -> likedByMe=false
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> res = postService.getAllPosts()
                .stream()
                .map(p -> toDto(p, null))
                .toList();

        return ResponseEntity.ok(res);
    }

    // ✅ Authenticated feed (viewer included) -> likedByMe accurate
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<List<PostResponse>> getAllPostsForMe(Principal principal) {
        String email = principal.getName();

        List<PostResponse> res = postService.getAllPosts()
                .stream()
                .map(p -> toDto(p, email))
                .toList();

        return ResponseEntity.ok(res);
    }

    // ✅ Public single post -> likedByMe=false
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(postService.getPostById(id), null));
    }

    // ✅ Auth single post -> likedByMe accurate
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/me")
    public ResponseEntity<PostResponse> getPostByIdForMe(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(toDto(postService.getPostById(id), principal.getName()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostByUserId(@PathVariable Long userId) {
        List<PostResponse> res = postService.getPostByUserId(userId)
                .stream()
                .map(p -> toDto(p, null))
                .toList();

        return ResponseEntity.ok(res);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, Principal principal) {
        postService.deletePostById(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}