package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public String getUserByEmail(String email) {

        User user = userRepo.findByEmail(email).orElse(null);

        if (user == null) {
            return "User not found with email: " + email;
        }

        return "User Found -> id: " + user.getId() +
               ", username: " + user.getUsername() +
               ", email: " + user.getEmail();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }
}