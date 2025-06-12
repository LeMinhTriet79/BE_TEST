package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Coaches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coachID;

    @OneToOne
    @JoinColumn(name = "userID", unique = true)
    private User user;

    @Column(nullable = false)
    private String fullName;

    private String specialization;

    @Column(columnDefinition = "LONGTEXT")
    private String bio;

    @Column(columnDefinition = "LONGTEXT")
    private String availability;

    private String profilePictureURL;

    @Column(nullable = false)
    private Boolean isActive = true;
}
