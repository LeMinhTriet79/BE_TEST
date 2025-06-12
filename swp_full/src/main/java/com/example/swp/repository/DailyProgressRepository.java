package com.example.swp.repository;

import com.example.swp.entity.DailyProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyProgressRepository extends JpaRepository<DailyProgress, Integer> {
}

