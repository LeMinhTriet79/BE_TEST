package com.example.swp.controller;

import com.example.swp.dto.UserDTO;
import com.example.swp.entity.*;
import com.example.swp.repository.*;
import com.example.swp.service.CoachService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coach")
public class CoachController {
    @Autowired private CoachService coachService;

    @GetMapping("/profile")
    public List<Coach> getAllCoaches() { return coachService.getAllCoaches(); }

    @GetMapping("/consultations")
    public List<Consultation> getAllConsultations() { return coachService.getAllConsultations(); }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(@PathVariable Integer id) { return coachService.getAllUsers(id); }

    @GetMapping("/daily-progress")
    public List<DailyProgress> getAllDailyProgress() { return coachService.getAllDailyProgress(); }
}
