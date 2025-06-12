package com.example.swp.repository;

import com.example.swp.entity.SmokingStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmokingStatusLogRepository extends JpaRepository<SmokingStatusLog, Integer> {
}

