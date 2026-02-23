package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.model.Like;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.LikeRepo;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepo;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepo likeRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public Like likePost(Like like) {

        Long userId = like.getUser().getId();
        Long postId = like.getPost().getId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found: " + postId));

        like.setUser(user);
        like.setPost(post);

        return likeRepo.save(like);
    }

    public void dislikePost(Long id) {
        likeRepo.deleteById(id);
    }
}