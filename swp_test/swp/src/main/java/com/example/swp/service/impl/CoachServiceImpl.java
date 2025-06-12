package com.example.swp.service.impl;

import com.example.swp.entity.Coach;
import com.example.swp.entity.Consultation;
import com.example.swp.entity.User;
import com.example.swp.entity.DailyProgress;
import com.example.swp.repository.CoachRepository;
import com.example.swp.repository.ConsultationRepository;
import com.example.swp.repository.UserRepository;
import com.example.swp.repository.DailyProgressRepository;
import com.example.swp.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired private CoachRepository coachRepository;
    @Autowired private ConsultationRepository consultationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private DailyProgressRepository dailyProgressRepository;

    @Override
    public List<Coach> getAllCoaches() { return coachRepository.findAll(); }

    @Override
    public List<Consultation> getAllConsultations() { return consultationRepository.findAll(); }

    @Override
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @Override
    public List<DailyProgress> getAllDailyProgress() { return dailyProgressRepository.findAll(); }
}

