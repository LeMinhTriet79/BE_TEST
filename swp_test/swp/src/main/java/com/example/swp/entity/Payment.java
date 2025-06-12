package com.example.swp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "packageID", nullable = false)
    private MembershipPackage packageInfo;

    @Column(nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(nullable = false)
    private Double amount;

    private String paymentMethod;

    @Column(unique = true)
    private String transactionID;

    @Column(nullable = false)
    private String status = "pending";
}
