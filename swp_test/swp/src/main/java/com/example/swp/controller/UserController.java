package com.example.swp.controller;

import com.example.swp.dto.UserDTO;
import com.example.swp.dto.UserMapper;
import com.example.swp.entity.User;
import com.example.swp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(UserMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return UserMapper.toDTO(userService.getUserById(id));
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return UserMapper.toDTO(userService.createUser(UserMapper.toEntity(userDTO)));
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return UserMapper.toDTO(userService.updateUser(id, UserMapper.toEntity(userDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
