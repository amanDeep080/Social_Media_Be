package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.ImageUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final ImageUploadService imageUploadService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/image")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        String url = imageUploadService.upload(file);
        return ResponseEntity.ok(Map.of("imageUrl", url));
    }
}