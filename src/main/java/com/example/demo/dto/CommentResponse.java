package com.example.demo.dto;

import java.time.Instant;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private Instant createdAt;

    private Long userId;
    private String username;
    private String fullName;
    private String email;
}