package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BlogPosts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private long postId;

    @ManyToOne
    @JoinColumn(name = "AuthorUserID", nullable = false)
    private User author;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "Content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "Excerpt", columnDefinition = "NVARCHAR(MAX)")
    private String excerpt;

    @Column(name = "PublishDate", nullable = false)
    private LocalDateTime publishDate;

    @Column(name = "LastModifiedDate")
    private LocalDateTime lastModifiedDate;

    @Column(name = "Category")
    private String category;

    @Column(name = "Tags")
    private String tags;

    @Column(name = "Views", nullable = false)
    private int views;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "FeaturedImageURL")
    private String featuredImageUrl;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL)
    private List<PostComment> comments;
} 