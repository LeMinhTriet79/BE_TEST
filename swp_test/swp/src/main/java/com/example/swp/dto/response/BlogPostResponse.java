package com.example.swp.dto.response;

import lombok.Data;

@Data
public class BlogPostResponse {

    private String title;
    private String content;
    private String imageUrl;
    // Add other fields you want to expose
}

