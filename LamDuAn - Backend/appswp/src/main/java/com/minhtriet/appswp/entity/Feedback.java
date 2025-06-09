package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Feedback", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"UserID", "TargetType", "TargetID"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackID")
    private long feedbackId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "TargetType", nullable = false)
    private String targetType;

    @Column(name = "TargetID", nullable = false)
    private long targetId;

    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "Comment", columnDefinition = "NVARCHAR(MAX)")
    private String comment;

    @Column(name = "SubmissionDate", nullable = false)
    private LocalDateTime submissionDate;
} 