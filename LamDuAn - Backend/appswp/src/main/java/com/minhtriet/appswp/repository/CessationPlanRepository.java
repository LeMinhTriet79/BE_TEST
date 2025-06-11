package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.CessationPlan;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CessationPlanRepository extends JpaRepository<CessationPlan, Long> {
    List<CessationPlan> findByUser(User user);
    List<CessationPlan> findByUserAndIsActiveTrue(User user);
}
