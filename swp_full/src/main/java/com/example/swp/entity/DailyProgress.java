package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "DailyProgress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer progressID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "planID")
    private CessationPlan plan;

    @Column(nullable = false)
    private LocalDate logDate;

    private Boolean smokedToday;
    private Integer cigarettesSmoked;
    private Integer cravingsLevel;
    private String mood;

    @Column(columnDefinition = "LONGTEXT")
    private String healthNotes;

    private Double moneySavedToday;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
