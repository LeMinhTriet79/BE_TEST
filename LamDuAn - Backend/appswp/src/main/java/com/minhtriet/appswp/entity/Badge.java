package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Badges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BadgeID")
    private long badgeId;

    @Column(name = "BadgeName", nullable = false, unique = true)
    private String badgeName;

    @Column(name = "IconURL")
    private String iconUrl;

    @Column(name = "Criteria", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String criteria;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "badge")
    private List<UserBadge> userBadges;
} 