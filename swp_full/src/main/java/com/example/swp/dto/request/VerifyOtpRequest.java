package com.example.swp.dto.request;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String otp;
}

