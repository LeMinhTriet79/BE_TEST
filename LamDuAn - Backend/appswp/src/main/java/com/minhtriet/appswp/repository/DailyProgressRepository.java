package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.DailyProgress;
import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.entity.CessationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {
    List<DailyProgress> findByUser(User user);
    List<DailyProgress> findByCessationPlan(CessationPlan plan);
    Optional<DailyProgress> findByUserAndLogDate(User user, LocalDate logDate);
}
