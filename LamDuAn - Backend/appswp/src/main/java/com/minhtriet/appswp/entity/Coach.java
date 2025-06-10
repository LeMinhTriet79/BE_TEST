package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Coaches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CoachID")
    private long coachId;

    // UserID unique, liên kết 1-1 với User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", unique = true)
    private User user;

    @Nationalized
    @Column(name = "FullName", nullable = false, length = 255)
    private String fullName;

    @Nationalized
    @Column(name = "Specialization", length = 255)
    private String specialization;

    @Nationalized
    @Column(name = "Bio", columnDefinition = "NVARCHAR(MAX)")
    private String bio;

    @Nationalized
    @Column(name = "Availability", columnDefinition = "NVARCHAR(MAX)")
    private String availability;

    @Column(name = "ProfilePictureURL", length = 255)
    private String profilePictureUrl;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;
}
