package com.example.demo.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final Cloudinary cloudinary;

    public String upload(MultipartFile file) {
        try {
            @SuppressWarnings("rawtypes")
            Map res = cloudinary.uploader().upload(file.getBytes(), Map.of(
                    "folder", "social_media_posts"
            ));
            return (String) res.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }
    }
}