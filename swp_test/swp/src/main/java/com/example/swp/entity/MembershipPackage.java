package com.example.swp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "MembershipPackages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageID;

    @Column(nullable = false, unique = true)
    private String packageName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer durationDays;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(nullable = false)
    private Boolean isActive = true;
}
