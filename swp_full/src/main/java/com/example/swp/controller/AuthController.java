package com.example.swp.controller;

import com.example.swp.dto.request.AuthRequest;
import com.example.swp.dto.response.AuthResponse;
import com.example.swp.dto.request.AuthRegisterRequest;
import com.example.swp.dto.UserDTO;
import com.example.swp.repository.UserRepository;
import com.example.swp.security.JwtUtil;
import com.example.swp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.swp.dto.request.ForgotPasswordRequest;
import com.example.swp.dto.request.ResetPasswordRequest;
import com.example.swp.dto.request.VerifyOtpRequest;
import com.example.swp.dto.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            userService.sendForgotPasswordOtp(request.getEmail());
            return ResponseEntity.ok("OTP sent to email if it exists.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to send OTP: " + ex.getMessage());
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            userService.resetPasswordWithOtp(request.getEmail(), request.getOtp(), request.getNewPassword());
            return ResponseEntity.ok("Password reset successful.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Password reset failed: " + ex.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest request) {
        userService.verifyOtp(request.getEmail(), request.getOtp());
        return "Account verified. You can now login.";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.registerWithOtp(request);
        return "Registration successful. Please check your email for OTP to verify your account.";
    }

    @GetMapping("/profile")
    public UserDTO getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }
}
