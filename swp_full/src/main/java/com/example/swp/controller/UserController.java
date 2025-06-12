package com.example.swp.controller;

import com.example.swp.dto.UserDTO;
import com.example.swp.dto.UserMapper;
import com.example.swp.dto.response.MembershipPackageResponse;
import com.example.swp.service.MembershipPackageService;
import com.example.swp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MembershipPackageService membershipPackageService;

    @GetMapping("/profile")
    public UserDTO getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return UserMapper.toDTO(userService.updateUser(id, UserMapper.toEntity(userDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/membership")
    public List<MembershipPackageResponse> getMembershipUsers() {
        return membershipPackageService.getAll();
    }

}
