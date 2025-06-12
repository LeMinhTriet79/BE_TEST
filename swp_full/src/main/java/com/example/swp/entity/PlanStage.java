package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PlanStages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stageID;

    @ManyToOne
    @JoinColumn(name = "planID", nullable = false)
    private CessationPlan plan;

    @Column(nullable = false)
    private String stageName;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private Integer targetDurationDays;

    @Column(nullable = false)
    private Integer sequenceOrder = 0;
}
