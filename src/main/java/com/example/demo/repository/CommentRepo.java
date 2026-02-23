package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    // Get all comments of a post
    List<Comment> findByPost_Id(Long postId);

    long countByPost_Id(Long postId);
}