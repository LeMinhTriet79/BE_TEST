package com.minhtriet.appswp.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // Lưu ý: SECRET_KEY cần dài ít nhất 32 ký tự (>= 256bit cho HS256)
    private final String SECRET_KEY = "very_long_secret_key_for_jwt_123456789!"; // Thay bằng bí mật thực sự của bạn

    private Key getSigningKey() {
        // JJWT mới yêu cầu Key dạng byte[] với độ dài >= 32 ký tự
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Tạo JWT từ username
    public String generateToken(String username) {
        long expiration = 86400000; // 1 ngày (ms)
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Dùng API mới!
                .compact();
    }

    // Lấy username từ token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Kiểm tra token hợp lệ
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
