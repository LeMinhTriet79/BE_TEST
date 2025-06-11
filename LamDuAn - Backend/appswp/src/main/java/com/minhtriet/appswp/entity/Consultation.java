package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Consultations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConsultationID")
    private long consultationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CoachID", nullable = false)
    private Coach coach;

    @Column(name = "ScheduledTime")
    private LocalDateTime scheduledTime;

    @Column(name = "Status", nullable = false, length = 255)
    private String status;

    @Column(name = "Notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;

    @Column(name = "MeetingLink", length = 255)
    private String meetingLink;
}
