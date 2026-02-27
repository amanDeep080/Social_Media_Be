package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CreateCommentRequest;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;

    @Transactional
    public Comment addComment(Long postId, CreateCommentRequest req, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment c = new Comment();
        c.setContent(req.getContent().trim());
        c.setPost(post);
        c.setUser(user);

        return commentRepo.save(c);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepo.findByPostIdOrderByCreatedAtDesc(postId);
    }

    public long commentCount(Long postId) {
        return commentRepo.countByPostId(postId);
    }

    @Transactional
    public void deleteComment(Long commentId, String email) {
        Comment c = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!c.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can delete only your own comment");
        }
        commentRepo.delete(c);
    }
}