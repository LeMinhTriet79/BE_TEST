package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String targetType;

    @Column(nullable = false)
    private Integer targetID;

    private Integer rating;

    @Column(columnDefinition = "LONGTEXT")
    private String comment;

    @Column(nullable = false)
    private LocalDateTime submissionDate = LocalDateTime.now();
}

