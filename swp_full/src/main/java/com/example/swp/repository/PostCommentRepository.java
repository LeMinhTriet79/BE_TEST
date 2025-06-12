package com.example.swp.repository;

import com.example.swp.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
}

