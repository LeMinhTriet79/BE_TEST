package com.example.swp.service;

import com.example.swp.entity.User;
import com.example.swp.dto.request.AuthRegisterRequest;
import com.example.swp.dto.UserDTO;
import com.example.swp.dto.request.RegisterRequest;
import org.springframework.security.core.Authentication;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
    UserDTO getProfile(Authentication authentication);

    // OTP, xác thực, đăng ký mới
    void sendForgotPasswordOtp(String email);
    void resetPasswordWithOtp(String email, String otp, String newPassword);
    void verifyOtp(String email, String otp);
    void registerWithOtp(RegisterRequest request);
}
