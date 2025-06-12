package com.example.swp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Payment> payments;
}
