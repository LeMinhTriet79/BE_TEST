package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CessationPlans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CessationPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID")
    private long planId;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(name = "SmokingFrequency", length = 255)
    private String smokingFrequency;

    @Column(name = "CostPerPack")
    private BigDecimal costPerPack;

    @Column(name = "Notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;

    @Column(name = "CustomDetails", columnDefinition = "NVARCHAR(MAX)")
    private String customDetails;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;

    // Liên kết với PlanStages
    @OneToMany(mappedBy = "cessationPlan")
    private List<PlanStage> stages;
}
