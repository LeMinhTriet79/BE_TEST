package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PlanStages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StageID")
    private long stageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PlanID", nullable = false)
    private CessationPlan cessationPlan;

    @Column(name = "StageName", nullable = false, length = 255)
    private String stageName;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "TargetDurationDays")
    private Integer targetDurationDays;

    @Column(name = "SequenceOrder", nullable = false)
    private Integer sequenceOrder = 0;
}
