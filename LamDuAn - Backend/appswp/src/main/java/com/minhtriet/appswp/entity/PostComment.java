package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PostComments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PostID", nullable = false)
    private BlogPost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentCommentID")
    private PostComment parentComment;

    @Column(name = "Content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "CommentDate", nullable = false)
    private LocalDateTime commentDate;

    @Column(name = "IsApproved", nullable = false)
    private boolean isApproved = true;

    @Column(name = "Upvotes", nullable = false)
    private int upvotes = 0;

    @Column(name = "Downvotes", nullable = false)
    private int downvotes = 0;
}
