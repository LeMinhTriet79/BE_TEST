package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Badge findByBadgeName(String badgeName);
}
