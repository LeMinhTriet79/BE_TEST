package com.minhtriet.appswp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.repository.UserRepository;
import com.minhtriet.appswp.repository.VerificationTokenRepository;
import com.minhtriet.appswp.service.UserService;
import com.minhtriet.appswp.util.JwtUtil;
import com.minhtriet.appswp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * API xác thực/đăng nhập/đăng ký/quên mật khẩu bằng OTP 4 số gửi về email.
 * - Đăng ký: Gửi OTP về email, nhập OTP xác thực mới lưu user.
 * - Quên mật khẩu: Gửi OTP về email, nhập OTP xác nhận mới đổi mật khẩu.
 * - Đăng nhập: Trả về JWT để FE sử dụng các API protected.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ===================== ĐĂNG KÝ 2 BƯỚC BẰNG OTP =======================

    /**
     * BƯỚC 1: Đăng ký - Gửi OTP về email
     * Endpoint: POST /api/auth/register-request
     */
    @PostMapping("/register-request")
    public ResponseEntity<Map<String, Object>> registerRequest(@RequestBody RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 1. Kiểm tra email đã tồn tại chưa
            if (userService.isEmailExists(request.getEmail())) {
                response.put("success", false);
                response.put("message", "Email đã được sử dụng");
                return ResponseEntity.badRequest().body(response);
            }
            // 2. Kiểm tra mật khẩu xác nhận
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                response.put("success", false);
                response.put("message", "Mật khẩu xác nhận không khớp");
                return ResponseEntity.badRequest().body(response);
            }
            // 3. Gửi OTP + lưu user tạm vào VerificationToken
            User user = new User();
            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordEncoder.encode(request.getPassword())); // Băm mật khẩu
            user.setUsername(request.getEmail());
            user.setRole("member");
            userService.registerUserWithOtp(user);

            response.put("success", true);
            response.put("message", "OTP đã được gửi về email. Vui lòng kiểm tra email để xác thực!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Đăng ký thất bại: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * BƯỚC 2: Xác nhận OTP để hoàn tất đăng ký (tạo user thật)
     * Endpoint: POST /api/auth/register-verify-otp
     */
    @PostMapping("/register-verify-otp")
    public ResponseEntity<Map<String, Object>> verifyRegisterOtp(@RequestBody VerifyOtpRequest request) {
        System.out.println("EMAIL: " + request.getEmail());
        System.out.println("OTP: " + request.getOtp());
        Map<String, Object> response = new HashMap<>();
        boolean ok = userService.verifyOtpAndRegister(request.getEmail(), request.getOtp());
        if (ok) {
            response.put("success", true);
            response.put("message", "Đăng ký thành công! Bạn đã có thể đăng nhập.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "OTP không đúng hoặc đã hết hạn!");
            return ResponseEntity.badRequest().body(response);
        }

    }

    // ===================== ĐĂNG NHẬP =======================

    /**
     * Đăng nhập bằng email & password.
     * Nếu đúng, trả về JWT token + user info cho FE.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Kiểm tra user tồn tại và đã xác thực email
            User user = userService.getUserByEmail(request.getEmail());
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email không tồn tại"));
            }
            if (!user.isEnabled()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Tài khoản chưa xác thực hoặc chưa hoàn tất đăng ký OTP!"));
            }
            // Kiểm tra mật khẩu (BCrypt)
            if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mật khẩu không đúng"));
            }

            // Xác thực qua Security để sinh JWT
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
            );

            // Sinh token JWT
            String token = jwtUtil.generateToken(user.getUsername());

            userService.updateLastLoginDate(user.getUserId());

            // Trả về token + user info cho FE
            return ResponseEntity.ok(new LoginResponse(
                    token,
                    user.getUserId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getRole(),
                    user.getProfilePictureUrl()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Đăng nhập thất bại: " + e.getMessage()));
        }
    }

    // ===================== QUÊN MẬT KHẨU 2 BƯỚC (OTP) =======================

    /**
     * BƯỚC 1: Gửi OTP xác nhận đổi mật khẩu về email
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody ForgotPasswordOtpRequest req) {
        userService.sendPasswordResetOtp(req.getEmail(), passwordEncoder.encode(req.getNewPassword()));
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Nếu email hợp lệ, mã OTP đã được gửi về email. Hãy kiểm tra hộp thư!"
        ));
    }

    /**
     * BƯỚC 2: Nhập OTP để đổi mật khẩu
     */
    @PostMapping("/reset-password-otp")
    public ResponseEntity<Map<String, Object>> resetPasswordOtp(@RequestBody VerifyOtpRequest req) {
        boolean ok = userService.verifyOtpAndResetPassword(req.getEmail(), req.getOtp());
        if (ok) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Đổi mật khẩu thành công!"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "OTP không đúng hoặc đã hết hạn!"));
        }
    }
}
