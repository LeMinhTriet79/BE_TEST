package com.example.swp.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String role;
    // Thêm các trường khác nếu cần
}

