package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CreatePostRequest;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Transactional
    public Post createPost(CreatePostRequest req, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post p = new Post();
        p.setContent(req.getContent().trim());
        p.setImageUrl(req.getImageUrl() == null || req.getImageUrl().trim().isEmpty() ? null : req.getImageUrl().trim());
        p.setUser(user);

        return postRepo.save(p);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getPostByUserId(Long userId) {
        return postRepo.findByUserId(userId);
    }

    @Transactional
    public void deletePostById(Long postId, String requesterEmail) {
        Post post = getPostById(postId);

        if (!post.getUser().getEmail().equals(requesterEmail)) {
            throw new RuntimeException("You can delete only your own post");
        }

        postRepo.delete(post);
    }
}