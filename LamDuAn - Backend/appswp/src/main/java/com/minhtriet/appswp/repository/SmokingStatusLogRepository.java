package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.SmokingStatusLog;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SmokingStatusLogRepository extends JpaRepository<SmokingStatusLog, Long> {
    List<SmokingStatusLog> findByUser(User user);
    List<SmokingStatusLog> findByUserAndLogDate(User user, LocalDate logDate);
}
