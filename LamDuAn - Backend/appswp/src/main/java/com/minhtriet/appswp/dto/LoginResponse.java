package com.minhtriet.appswp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private long userId;
    private String fullName;
    private String email;
    private String role;
    private String profilePictureUrl;
}
