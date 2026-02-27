package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Follow;
import com.example.demo.model.User;
import com.example.demo.repository.FollowRepo;
import com.example.demo.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepo followRepo;
    private final UserRepo userRepo;

    @Transactional
    public boolean toggleFollow(Long followerId, Long followingId) {

        if (followerId.equals(followingId)) {
            throw new RuntimeException("You cannot follow yourself");
        }

        // ensure target exists
        User follower = userRepo.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));

        User following = userRepo.findById(followingId)
                .orElseThrow(() -> new RuntimeException("Target user not found"));

        // toggle
        return followRepo.findByFollowerIdAndFollowingId(followerId, followingId)
                .map(existing -> {
                    followRepo.delete(existing);
                    return false; // unfollowed
                })
                .orElseGet(() -> {
                    Follow f = new Follow();
                    f.setFollower(follower);
                    f.setFollowing(following);
                    followRepo.save(f);
                    return true; // followed
                });
    }

    public long followersCount(Long userId) {
        return followRepo.countByFollowingId(userId);
    }

    public long followingCount(Long userId) {
        return followRepo.countByFollowerId(userId);
    }
}