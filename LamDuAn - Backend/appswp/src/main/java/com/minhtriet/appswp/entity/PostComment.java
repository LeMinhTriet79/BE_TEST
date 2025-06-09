package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PostComments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private long commentId;

    @ManyToOne
    @JoinColumn(name = "PostID", nullable = false)
    private BlogPost blogPost;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ParentCommentID")
    private PostComment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<PostComment> replies;

    @Column(name = "Content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "CommentDate", nullable = false)
    private LocalDateTime commentDate;

    @Column(name = "IsApproved", nullable = false)
    private boolean isApproved;

    @Column(name = "Upvotes", nullable = false)
    private int upvotes;

    @Column(name = "Downvotes", nullable = false)
    private int downvotes;
} 