package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PostResponse {
    private Long id;
    private String content;
    private String imageUrl;

    private Long userId;
    private String username;
    private String fullName;
    private String email;

    private long likeCount;
    private long commentCount;
    private boolean likedByMe;
}