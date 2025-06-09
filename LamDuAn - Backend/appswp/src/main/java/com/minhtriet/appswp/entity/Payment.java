package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private long paymentId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PackageID", nullable = false)
    private MembershipPackage membershipPackage;

    @Column(name = "PaymentDate", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "TransactionID", unique = true)
    private String transactionId;

    @Column(name = "Status", nullable = false)
    private String status;
} 