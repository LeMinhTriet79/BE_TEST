package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.BlogPost;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthor(User user);
    BlogPost findBySlug(String slug);
}
