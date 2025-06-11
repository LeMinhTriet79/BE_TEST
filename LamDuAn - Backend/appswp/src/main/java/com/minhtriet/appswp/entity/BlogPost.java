package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BlogPosts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AuthorUserID", nullable = false)
    private User author;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Slug", unique = true, nullable = false, length = 255)
    private String slug;

    @Column(name = "Content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "Excerpt", columnDefinition = "NVARCHAR(MAX)")
    private String excerpt;

    @Column(name = "PublishDate", nullable = false)
    private LocalDateTime publishDate;

    @Column(name = "LastModifiedDate")
    private LocalDateTime lastModifiedDate;

    @Column(name = "Category", length = 255)
    private String category;

    @Column(name = "Tags", length = 255)
    private String tags;

    @Column(name = "Views", nullable = false)
    private int views = 0;

    @Column(name = "Status", nullable = false, length = 255)
    private String status = "draft";

    @Column(name = "FeaturedImageURL", length = 255)
    private String featuredImageUrl;
}
