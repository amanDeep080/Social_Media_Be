package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    private CommentResponse toDto(Comment c) {
        return new CommentResponse(
                c.getId(),
                c.getContent(),
                c.getCreatedAt(),
                c.getUser().getId(),
                c.getUser().getUsername(),
                c.getUser().getFullName(),
                c.getUser().getEmail()
        );
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable("postId") Long postId) {

        return ResponseEntity.ok(
                commentService.getComments(postId).stream().map(this::toDto).toList()
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody CreateCommentRequest req,
            Principal principal) {

        Comment c = commentService.addComment(postId, req, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(c));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Principal principal) {
        commentService.deleteComment(commentId, principal.getName());
        return ResponseEntity.noContent().build();
    }
}