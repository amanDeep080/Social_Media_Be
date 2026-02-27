package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMiniResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
}