package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Consultation;
import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByUser(User user);
    List<Consultation> findByCoach(Coach coach);
}
