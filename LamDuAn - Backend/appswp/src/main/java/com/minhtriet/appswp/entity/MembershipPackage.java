package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "MembershipPackages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PackageID")
    private long packageId;

    @Column(name = "PackageName", nullable = false, unique = true)
    private String packageName;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "DurationDays", nullable = false)
    private int durationDays;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "membershipPackage")
    private List<Payment> payments;
} 