package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final PostLikeRepo likeRepo;

    @Transactional
    public boolean toggleLike(Long postId, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return likeRepo.findByPostIdAndUserId(post.getId(), user.getId())
                .map(existing -> {
                    likeRepo.delete(existing);
                    return false; // unliked
                })
                .orElseGet(() -> {
                    PostLike like = new PostLike();
                    like.setPost(post);
                    like.setUser(user);
                    likeRepo.save(like);
                    return true; // liked
                });
    }

    public long likeCount(Long postId) {
        return likeRepo.countByPostId(postId);
    }

    public boolean likedByMe(Long postId, Long userId) {
        return likeRepo.existsByPostIdAndUserId(postId, userId);
    }
}