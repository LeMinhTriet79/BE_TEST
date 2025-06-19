package com.example.SWP_Backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CessationPlans")
public class CessationPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID")
    private Long planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "ReasonToQuit", columnDefinition = "nvarchar(max)")
    private String reasonToQuit;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "TargetQuitDate")
    private LocalDate targetQuitDate;

    @Column(name = "CigarettesPerDay")
    private Integer cigarettesPerDay;

    @Column(name = "SmokingFrequency")
    private String smokingFrequency;

    @Column(name = "CostPerPack")
    private BigDecimal costPerPack;

    @Column(name = "Notes", columnDefinition = "nvarchar(max)")
    private String notes;

    @Column(name = "CustomDetails", columnDefinition = "nvarchar(max)")
    private String customDetails;

    @Column(name = "IsActive")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "cessationPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanStage> stages;

    // Getters & Setters

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReasonToQuit() {
        return reasonToQuit;
    }

    public void setReasonToQuit(String reasonToQuit) {
        this.reasonToQuit = reasonToQuit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getTargetQuitDate() {
        return targetQuitDate;
    }

    public void setTargetQuitDate(LocalDate targetQuitDate) {
        this.targetQuitDate = targetQuitDate;
    }

    public Integer getCigarettesPerDay() {
        return cigarettesPerDay;
    }

    public void setCigarettesPerDay(Integer cigarettesPerDay) {
        this.cigarettesPerDay = cigarettesPerDay;
    }

    public String getSmokingFrequency() {
        return smokingFrequency;
    }

    public void setSmokingFrequency(String smokingFrequency) {
        this.smokingFrequency = smokingFrequency;
    }

    public BigDecimal getCostPerPack() {
        return costPerPack;
    }

    public void setCostPerPack(BigDecimal costPerPack) {
        this.costPerPack = costPerPack;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCustomDetails() {
        return customDetails;
    }

    public void setCustomDetails(String customDetails) {
        this.customDetails = customDetails;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<PlanStage> getStages() {
        return stages;
    }

    public void setStages(List<PlanStage> stages) {
        this.stages = stages;
    }
}
