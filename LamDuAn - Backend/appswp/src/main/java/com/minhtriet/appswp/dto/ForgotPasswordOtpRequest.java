package com.minhtriet.appswp.dto;

import lombok.Data;

@Data
public class ForgotPasswordOtpRequest {
    private String email;
    private String newPassword;
}
