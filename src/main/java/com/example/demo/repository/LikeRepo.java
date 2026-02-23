package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Like;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {

    // Prevent duplicate likes
    Optional<Like> findByUser_IdAndPost_Id(Long userId, Long postId);

    // Count likes on a post
    long countByPost_Id(Long postId);
}