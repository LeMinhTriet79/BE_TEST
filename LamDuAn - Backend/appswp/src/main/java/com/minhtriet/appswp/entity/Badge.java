package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Badges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BadgeID")
    private long badgeId;

    @Column(name = "BadgeName", unique = true, nullable = false, length = 255)
    private String badgeName;

    @Column(name = "IconURL", length = 255)
    private String iconUrl;

    @Column(name = "Criteria", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String criteria;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;
}
