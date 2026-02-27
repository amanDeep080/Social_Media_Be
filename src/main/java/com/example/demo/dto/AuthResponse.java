package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String message;
}