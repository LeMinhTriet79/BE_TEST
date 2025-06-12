package com.example.swp.repository;

import com.example.swp.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
}

