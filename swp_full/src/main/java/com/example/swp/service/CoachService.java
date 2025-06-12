package com.example.swp.service;

import com.example.swp.dto.UserDTO;
import com.example.swp.entity.Coach;
import com.example.swp.entity.Consultation;
import com.example.swp.entity.User;
import com.example.swp.entity.DailyProgress;
import java.util.List;

public interface CoachService {
    List<Coach> getAllCoaches();
    List<Consultation> getAllConsultations();
    List<UserDTO> getAllUsers(Integer id);
    List<DailyProgress> getAllDailyProgress();
}

