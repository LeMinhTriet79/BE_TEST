package com.example.swp.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String role;
    // Thêm các trường khác nếu cần
}

