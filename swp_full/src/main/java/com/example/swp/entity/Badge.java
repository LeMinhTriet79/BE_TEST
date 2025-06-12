package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Badges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer badgeID;

    @Column(nullable = false, unique = true)
    private String badgeName;

    private String iconURL;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String criteria;

    @Column(nullable = false)
    private Boolean isActive = true;
}

