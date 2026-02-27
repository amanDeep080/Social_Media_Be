package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreatePostRequest {

    @NotBlank
    private String content;

    private String imageUrl; // optional
}