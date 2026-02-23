package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;   // ✅ ADD THIS

    public Post createPost(Post post) {

        if (post.getUser() == null || post.getUser().getId() == null) {
            throw new BadRequestException("User id is required to create post");
        }

        Long userId = post.getUser().getId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found with id: " + userId));

        post.setUser(user);

        return postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public List<Post> getPostByUserId(Long userId) {
        return postRepo.findByUser_Id(userId);
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Post not found with id: " + id));
    }

    public void deletePostById(Long id) {
        postRepo.deleteById(id);
    }
}