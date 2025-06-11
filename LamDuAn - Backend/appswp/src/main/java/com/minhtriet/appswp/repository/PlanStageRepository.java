package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.PlanStage;
import com.minhtriet.appswp.entity.CessationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanStageRepository extends JpaRepository<PlanStage, Long> {
    List<PlanStage> findByCessationPlan(CessationPlan plan);
}
