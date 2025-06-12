package com.example.swp.repository;

import com.example.swp.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    List<Consultation> findByUser_UserID(Integer userId);
}

