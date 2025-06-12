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
    @PostMapping("/register")
    public UserDTO register(@RequestBody AuthRegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/profile")
    public UserDTO getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }
}
