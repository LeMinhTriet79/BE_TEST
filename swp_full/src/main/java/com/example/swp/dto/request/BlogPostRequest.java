package com.example.swp.dto.request;

import lombok.Data;

@Data
public class BlogPostRequest {
    private String title;
    private String content;
    private String imageUrl;
    // Thêm các trường khác nếu cần
}

