package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PostComments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentID;

    @ManyToOne
    @JoinColumn(name = "postID", nullable = false)
    private BlogPost post;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "parentCommentID")
    private PostComment parentComment;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime commentDate = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean isApproved = true;

    @Column(nullable = false)
    private Integer upvotes = 0;

    @Column(nullable = false)
    private Integer downvotes = 0;
}

