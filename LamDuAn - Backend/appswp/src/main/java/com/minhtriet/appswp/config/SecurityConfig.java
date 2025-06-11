package com.minhtriet.appswp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Cấu hình bảo mật cho toàn bộ ứng dụng.
     * - Tắt CSRF (do thường làm API REST, client FE tách biệt).
     * - Cho phép truy cập public tới Swagger, OpenAPI, và các API auth (login/register/forgot password...).
     * - Các endpoint còn lại bắt buộc phải xác thực (authenticated).
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập swagger-ui và openapi
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/api/membership-packages/**"
                        ).permitAll()
                        // Cho phép truy cập auth (đăng nhập, đăng ký)
                        .requestMatchers("/api/auth/**").permitAll()
                        // CHO PHÉP TẠM THỜI test public user-api
                        .requestMatchers("/api/user/**").permitAll()
                        // ... thêm các endpoint khác nếu cần
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    /**
     * Bean quản lý xác thực của Spring Security.
     * Được sử dụng cho các chức năng xác thực login bằng Username/Password.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Bean mã hóa mật khẩu (PasswordEncoder).
     * Sử dụng thuật toán BCrypt - là chuẩn phổ biến để hash password (không lưu plain text).
     * Tất cả nơi lưu/truy cập password đều nên dùng bean này.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
