package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ToggleResponse {
    private boolean active; // liked/following
}