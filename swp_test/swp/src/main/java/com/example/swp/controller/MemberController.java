package com.example.swp.controller;

import com.example.swp.entity.*;
import com.example.swp.service.MemberService;
import com.example.swp.dto.request.ConsultationBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@PreAuthorize("hasRole('MEMBER')")
public class MemberController {
    @Autowired private MemberService memberService;

    @GetMapping("/membership-packages")
    public List<MembershipPackage> getAllMembershipPackages() { return memberService.getAllMembershipPackages(); }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() { return memberService.getAllPayments(); }

    @GetMapping("/smoking-status-logs")
    public List<SmokingStatusLog> getAllSmokingStatusLogs() { return memberService.getAllSmokingStatusLogs(); }

    @GetMapping("/cessation-plans")
    public List<CessationPlan> getAllCessationPlans() { return memberService.getAllCessationPlans(); }

    @GetMapping("/plan-stages")
    public List<PlanStage> getAllPlanStages() { return memberService.getAllPlanStages(); }

    @GetMapping("/daily-progress")
    public List<DailyProgress> getAllDailyProgress() { return memberService.getAllDailyProgress(); }

    @GetMapping("/user-badges")
    public List<UserBadge> getAllUserBadges() { return memberService.getAllUserBadges(); }

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() { return memberService.getAllNotifications(); }

    @GetMapping("/consultations")
    public List<Consultation> getAllConsultations() { return memberService.getAllConsultations(); }

    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() { return memberService.getAllFeedbacks(); }

    @GetMapping("/blog-posts")
    public List<BlogPost> getAllBlogPosts() { return memberService.getAllBlogPosts(); }

    @GetMapping("/post-comments")
    public List<PostComment> getAllPostComments() { return memberService.getAllPostComments(); }

    @PostMapping("/consultations")
    public Consultation bookConsultation(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ConsultationBookingRequest request) {
        // Assuming username is the userId or can be mapped to userId
        Integer userId = Integer.parseInt(userDetails.getUsername());
        return memberService.bookConsultation(userId, request);
    }
}
