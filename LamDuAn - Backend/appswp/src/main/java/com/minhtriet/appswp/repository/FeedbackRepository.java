package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Feedback;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
    List<Feedback> findByTargetTypeAndTargetId(String targetType, Long targetId);
    boolean existsByUserAndTargetTypeAndTargetId(User user, String targetType, Long targetId);
}
