package com.example.swp.repository;

import com.example.swp.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}

