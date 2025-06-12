package com.example.swp.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserBadges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userBadgeID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "badgeID", nullable = false)
    private Badge badge;

    @Column(nullable = false)
    private LocalDateTime dateAwarded = LocalDateTime.now();
}
