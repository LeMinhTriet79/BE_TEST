package com.example.swp.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BlogPosts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postID;

    @ManyToOne
    @JoinColumn(name = "authorUserID", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    private String excerpt;
    private LocalDateTime publishDate = LocalDateTime.now();
    private LocalDateTime lastModifiedDate;
    private String category;
    private String tags;

    @Column(nullable = false)
    private Integer views = 0;

    @Column(nullable = false)
    private String status = "draft";

    private String featuredImageURL;
}
