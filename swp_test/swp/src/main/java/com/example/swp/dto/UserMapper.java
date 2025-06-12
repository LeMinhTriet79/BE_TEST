package com.example.swp.dto;

import com.example.swp.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }
}

