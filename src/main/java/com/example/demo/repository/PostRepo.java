package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}