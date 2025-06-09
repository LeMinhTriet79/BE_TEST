package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Coaches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CoachID")
    private long coachId;

    @OneToOne
    @JoinColumn(name = "UserID", unique = true)
    private User user;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Specialization")
    private String specialization;

    @Column(name = "Bio", columnDefinition = "NVARCHAR(MAX)")
    private String bio;

    @Column(name = "Availability", columnDefinition = "NVARCHAR(MAX)")
    private String availability;

    @Column(name = "ProfilePictureURL")
    private String profilePictureUrl;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "coach")
    private List<Consultation> consultations;
} 