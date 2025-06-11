package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.UserBadge;
import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
    List<UserBadge> findByUser(User user);
    boolean existsByUserAndBadge(User user, Badge badge);
}
