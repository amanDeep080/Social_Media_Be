package com.example.demo.dto;

import com.example.demo.model.UserRole;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RoleUpdateRequest {
    private UserRole role; // USER or ADMIN
}