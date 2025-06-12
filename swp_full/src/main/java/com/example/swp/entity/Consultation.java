package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consultationID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "coachID", nullable = false)
    private Coach coach;

    private LocalDateTime scheduledTime;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "LONGTEXT")
    private String notes;

    private String meetingLink;
}
