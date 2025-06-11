package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SmokingStatusLogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmokingStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID")
    private long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "LogDate", nullable = false)
    private LocalDate logDate;

    @Column(name = "CigarettesPerDay")
    private Integer cigarettesPerDay;

    @Column(name = "SmokingFrequency", length = 255)
    private String smokingFrequency;

    @Column(name = "CostPerPack")
    private BigDecimal costPerPack;

    @Column(name = "Notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;
}
