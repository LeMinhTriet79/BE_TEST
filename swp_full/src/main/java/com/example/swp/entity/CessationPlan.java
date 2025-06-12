package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "CessationPlans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CessationPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(columnDefinition = "LONGTEXT")
    private String reasonToQuit;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate targetQuitDate;

    private Integer cigarettesPerDay;
    private String smokingFrequency;
    private Double costPerPack;

    @Column(columnDefinition = "LONGTEXT")
    private String notes;

    @Column(columnDefinition = "LONGTEXT")
    private String customDetails;

    @Column(nullable = false)
    private Boolean isActive = true;
}
