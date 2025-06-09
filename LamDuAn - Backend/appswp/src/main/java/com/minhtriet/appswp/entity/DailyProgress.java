package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "DailyProgress", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"UserID", "LogDate"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgressID")
    private long progressId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
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

    @Column(name = "Mood")
    private String mood;

    @Column(name = "HealthNotes", columnDefinition = "NVARCHAR(MAX)")
    private String healthNotes;

    @Column(name = "MoneySavedToday", precision = 10, scale = 2)
    private BigDecimal moneySavedToday;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;
} 