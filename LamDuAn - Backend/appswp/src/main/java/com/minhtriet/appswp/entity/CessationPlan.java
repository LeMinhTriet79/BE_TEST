package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CessationPlans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CessationPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID")
    private long planId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "ReasonToQuit", columnDefinition = "NVARCHAR(MAX)")
    private String reasonToQuit;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "TargetQuitDate")
    private LocalDate targetQuitDate;

    @Column(name = "CigarettesPerDay")
    private Integer cigarettesPerDay;

    @Column(name = "SmokingFrequency")
    private String smokingFrequency;

    @Column(name = "CostPerPack", precision = 10, scale = 2)
    private BigDecimal costPerPack;

    @Column(name = "Notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;

    @Column(name = "CustomDetails", columnDefinition = "NVARCHAR(MAX)")
    private String customDetails;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "cessationPlan", cascade = CascadeType.ALL)
    private List<PlanStage> planStages;

    @OneToMany(mappedBy = "cessationPlan")
    private List<DailyProgress> dailyProgresses;
} 