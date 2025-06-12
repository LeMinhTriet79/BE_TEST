package com.example.swp.service.impl;

import com.example.swp.dto.request.AuthRegisterRequest;
import com.example.swp.dto.UserDTO;
import com.example.swp.dto.UserMapper;
import com.example.swp.entity.User;
import com.example.swp.repository.UserRepository;
import com.example.swp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOtpEmail(String to, String otp, String subject) {
        if (mailSender == null) return;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }

    @Override
    public List<User> getAllUsers() {
        // Chỉ trả về user chưa bị xóa mềm
        return userRepository.findAll().stream()
                .filter(u -> u.getDeleted() == null || !u.getDeleted())
                .toList();
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        if (user.getDeleted() != null && user.getDeleted()) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return user;
    }


    @Override
    public User updateUser(Integer id, User user) {
        User existing = getUserById(id);
        // Không cho phép cập nhật nếu user đã bị xóa mềm
        if (existing.getDeleted() != null && existing.getDeleted()) {
            throw new IllegalArgumentException("Cannot update a deleted user");
        }
        // Kiểm tra điều kiện: không cho phép cập nhật username/email trùng với user khác
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            if (!u.getUserID().equals(id)) {
                throw new IllegalArgumentException("Username already exists");
            }
        });
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            if (!u.getUserID().equals(id)) {
                throw new IllegalArgumentException("Email already exists");
            }
        });
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }



    @Override
    public UserDTO getProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public void sendForgotPasswordOtp(String email) {
        var userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            // Không tiết lộ email không tồn tại vì lý do bảo mật
            return;
        }
        User user = userOpt.get();
        String otp = generateOtp();
        user.setOtpCode(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
        userRepository.save(user);
        sendOtpEmail(email, otp, "Reset Password OTP");
    }

    @Override
    public void resetPasswordWithOtp(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if (user.getOtpCode() == null || user.getOtpExpiry() == null ||
            !user.getOtpCode().equals(otp) || user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setOtpCode(null);
        user.setOtpExpiry(null);
        userRepository.save(user);
    }

    @Override
    public void verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if (user.getOtpCode() == null || user.getOtpExpiry() == null ||
            !user.getOtpCode().equals(otp) || user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }
        user.setVerified(true);
        user.setOtpCode(null);
        user.setOtpExpiry(null);
        userRepository.save(user);
    }

    @Override
    public void registerWithOtp(com.example.swp.dto.request.RegisterRequest request) {
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        String otp = generateOtp();
        user.setOtpCode(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
        userRepository.save(user);
        sendOtpEmail(request.getEmail(), otp, "Account Registration OTP");
    }
}
