package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "DailyProgress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgressID")
    private long progressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PlanID")
    private CessationPlan cessationPlan;

    @Column(name = "LogDate", nullable = false)
    private LocalDate logDate;

    @Column(name = "SmokedToday")
    private Boolean smokedToday;

    @Column(name = "CigarettesSmoked")
    private Integer cigarettesSmoked;

    @Column(name = "CravingsLevel")
    private Integer cravingsLevel;

    @Column(name = "Mood", length = 255)
    private String mood;

    @Column(name = "HealthNotes", columnDefinition = "NVARCHAR(MAX)")
    private String healthNotes;

    @Column(name = "MoneySavedToday")
    private BigDecimal moneySavedToday;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;
}
