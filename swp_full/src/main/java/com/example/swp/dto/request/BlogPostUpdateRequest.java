package com.example.swp.dto.request;

import lombok.Data;

@Data
public class BlogPostUpdateRequest {
    private String title;
    private String content;
    private String imageUrl;
    // Add other fields as needed
}

