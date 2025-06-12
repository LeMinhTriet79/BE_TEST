package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SmokingStatusLogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmokingStatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate logDate = LocalDate.now();

    private Integer cigarettesPerDay;
    private String smokingFrequency;
    private Double costPerPack;

    @Column(columnDefinition = "LONGTEXT")
    private String notes;
}
