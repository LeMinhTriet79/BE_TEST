package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserBadges", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"UserID", "BadgeID"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserBadgeID")
    private long userBadgeId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BadgeID", nullable = false)
    private Badge badge;

    @Column(name = "DateAwarded", nullable = false)
    private LocalDateTime dateAwarded;
} 