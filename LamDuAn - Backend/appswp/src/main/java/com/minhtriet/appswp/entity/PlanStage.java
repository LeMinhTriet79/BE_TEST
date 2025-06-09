package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PlanStages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StageID")
    private long stageId;

    @ManyToOne
    @JoinColumn(name = "PlanID", nullable = false)
    private CessationPlan cessationPlan;

    @Column(name = "StageName", nullable = false)
    private String stageName;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "TargetDurationDays")
    private Integer targetDurationDays;

    @Column(name = "SequenceOrder", nullable = false)
    private int sequenceOrder;
} 