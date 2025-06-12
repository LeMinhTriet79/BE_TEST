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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public User createUser(User user) {
        return userRepository.save(user);
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
    public UserDTO register(AuthRegisterRequest request) {
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
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserMapper.toDTO(user);
    }
}
