package com.example.swp.service;

import com.example.swp.dto.request.ConsultationBookingRequest;
import com.example.swp.entity.*;
import java.util.List;

public interface MemberService {
    List<MembershipPackage> getAllMembershipPackages();
    List<Payment> getAllPayments();
    List<SmokingStatusLog> getAllSmokingStatusLogs();
    List<CessationPlan> getAllCessationPlans();
    List<PlanStage> getAllPlanStages();
    List<DailyProgress> getAllDailyProgress();
    List<UserBadge> getAllUserBadges();
    List<Notification> getAllNotifications();
    List<Consultation> getAllConsultations();
    List<Feedback> getAllFeedbacks();
    List<BlogPost> getAllBlogPosts();
    List<PostComment> getAllPostComments();
    Consultation bookConsultation(Integer userId, ConsultationBookingRequest request);
}

