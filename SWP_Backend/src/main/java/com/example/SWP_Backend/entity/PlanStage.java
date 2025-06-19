package com.example.SWP_Backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PlanStages")
public class PlanStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StageID")
    private Long stageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PlanID", nullable = false)
    private CessationPlan cessationPlan;

    @Column(name = "StageName")
    private String stageName;

    @Column(name = "Description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(name = "TargetDurationDays")
    private Integer targetDurationDays;

    @Column(name = "SequenceOrder")
    private Integer sequenceOrder = 0;

    // Getters & Setters

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public CessationPlan getCessationPlan() {
        return cessationPlan;
    }

    public void setCessationPlan(CessationPlan cessationPlan) {
        this.cessationPlan = cessationPlan;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTargetDurationDays() {
        return targetDurationDays;
    }

    public void setTargetDurationDays(Integer targetDurationDays) {
        this.targetDurationDays = targetDurationDays;
    }

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }
}
