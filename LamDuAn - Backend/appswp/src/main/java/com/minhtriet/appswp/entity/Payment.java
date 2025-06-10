package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private long paymentId;

    // Mapping với User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    // Mapping với MembershipPackage
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID", nullable = false)
    private MembershipPackage membershipPackage;

    @Column(name = "PaymentDate", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "PaymentMethod", length = 255)
    private String paymentMethod;

    @Column(name = "TransactionID", unique = true, length = 255)
    private String transactionId;

    @Column(name = "Status", nullable = false, length = 255)
    private String status = "pending";

    @PrePersist
    protected void onCreate() {
        if (paymentDate == null) {
            paymentDate = LocalDateTime.now();
        }
        if (status == null || status.trim().isEmpty()) {
            status = "pending";
        }
    }
}
