package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    // Get all posts of a user
    List<Post> findByUser_Id(Long userId);

}