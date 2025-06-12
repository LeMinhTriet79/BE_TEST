package com.example.swp.service;

import com.example.swp.entity.User;
import com.example.swp.dto.request.AuthRegisterRequest;
import com.example.swp.dto.UserDTO;
import org.springframework.security.core.Authentication;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
    UserDTO register(AuthRegisterRequest request);
    UserDTO getProfile(Authentication authentication);
}

