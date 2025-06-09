package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SmokingStatusLogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmokingStatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID")
    private long logId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "LogDate", nullable = false)
    private LocalDate logDate;

    @Column(name = "CigarettesPerDay")
    private Integer cigarettesPerDay;

    @Column(name = "SmokingFrequency")
    private String smokingFrequency;

    @Column(name = "CostPerPack", precision = 10, scale = 2)
    private BigDecimal costPerPack;

    @Column(name = "Notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;
} 