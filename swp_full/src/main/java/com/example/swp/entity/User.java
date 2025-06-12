package com.example.swp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String email;

    private String fullName;

    @Column(nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    private LocalDateTime lastLoginDate;
    private String profilePictureURL;

    @ManyToOne
    @JoinColumn(name = "currentMembershipPackageID")
    private MembershipPackage currentMembershipPackage;

    private LocalDate subscriptionEndDate;

    @ManyToOne
    @JoinColumn(name = "coachID")
    private Coach coach;

    @Column(nullable = false)
    private String role = "guest";

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(nullable = false)
    private boolean isVerified = false; // Xác thực email/đăng ký

    private String otpCode; // Mã OTP

    private LocalDateTime otpExpiry; // Thời gian hết hạn OTP
}
