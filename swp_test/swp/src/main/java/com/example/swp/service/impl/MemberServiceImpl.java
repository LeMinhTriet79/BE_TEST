package com.example.swp.service.impl;

import com.example.swp.entity.*;
import com.example.swp.repository.*;
import com.example.swp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.swp.dto.request.ConsultationBookingRequest;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired private MembershipPackageRepository membershipPackageRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private SmokingStatusLogRepository smokingStatusLogRepository;
    @Autowired private CessationPlanRepository cessationPlanRepository;
    @Autowired private PlanStageRepository planStageRepository;
    @Autowired private DailyProgressRepository dailyProgressRepository;
    @Autowired private UserBadgeRepository userBadgeRepository;
    @Autowired private NotificationRepository notificationRepository;
    @Autowired private ConsultationRepository consultationRepository;
    @Autowired private FeedbackRepository feedbackRepository;
    @Autowired private BlogPostRepository blogPostRepository;
    @Autowired private PostCommentRepository postCommentRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CoachRepository coachRepository;

    @Override
    public List<MembershipPackage> getAllMembershipPackages() { return membershipPackageRepository.findAll(); }
    @Override
    public List<Payment> getAllPayments() { return paymentRepository.findAll(); }
    @Override
    public List<SmokingStatusLog> getAllSmokingStatusLogs() { return smokingStatusLogRepository.findAll(); }
    @Override
    public List<CessationPlan> getAllCessationPlans() { return cessationPlanRepository.findAll(); }
    @Override
    public List<PlanStage> getAllPlanStages() { return planStageRepository.findAll(); }
    @Override
    public List<DailyProgress> getAllDailyProgress() { return dailyProgressRepository.findAll(); }
    @Override
    public List<UserBadge> getAllUserBadges() { return userBadgeRepository.findAll(); }
    @Override
    public List<Notification> getAllNotifications() { return notificationRepository.findAll(); }
    @Override
    public List<Consultation> getAllConsultations() {
        // Only return consultations for the current authenticated user
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Integer userId = Integer.parseInt(username);
        return consultationRepository.findByUser_UserID(userId);
    }
    @Override
    public List<Feedback> getAllFeedbacks() { return feedbackRepository.findAll(); }
    @Override
    public List<BlogPost> getAllBlogPosts() { return blogPostRepository.findAll(); }
    @Override
    public List<PostComment> getAllPostComments() { return postCommentRepository.findAll(); }

    @Override
    public Consultation bookConsultation(Integer userId, ConsultationBookingRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Coach coach = coachRepository.findById(request.getCoachId()).orElseThrow(() -> new RuntimeException("Coach not found"));
        Consultation consultation = new Consultation();
        consultation.setUser(user);
        consultation.setCoach(coach);
        consultation.setScheduledTime(request.getScheduledTime());
        consultation.setStatus("PENDING");
        consultation.setNotes(request.getNotes());
        return consultationRepository.save(consultation);
    }
}
