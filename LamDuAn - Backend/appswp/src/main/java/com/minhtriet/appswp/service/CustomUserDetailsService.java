package com.minhtriet.appswp.service;

import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Hàm này để Security gọi khi cần xác thực username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        // Trả về user details chuẩn của Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole() == null ? "member" : user.getRole())
                .accountLocked(!user.isEnabled())
                .build();
    }
}
