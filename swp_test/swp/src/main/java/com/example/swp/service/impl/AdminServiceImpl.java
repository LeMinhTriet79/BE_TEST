package com.example.swp.service.impl;

import com.example.swp.entity.*;
import com.example.swp.repository.*;
import com.example.swp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.swp.dto.response.BlogPostResponse;
import com.example.swp.dto.request.BadgeRequest;
import com.example.swp.dto.response.BadgeResponse;
import com.example.swp.dto.request.UserRequest;
import com.example.swp.dto.response.UserResponse;
import com.example.swp.dto.request.BlogPostRequest;
import com.example.swp.dto.request.MembershipPackageRequest;
import com.example.swp.dto.response.MembershipPackageResponse;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private UserRepository userRepository;
    @Autowired private MembershipPackageRepository membershipPackageRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private BlogPostRepository blogPostRepository;
    @Autowired private PostCommentRepository postCommentRepository;
    @Autowired private FeedbackRepository feedbackRepository;
    @Autowired private BadgeRepository badgeRepository;
    @Autowired private DailyProgressRepository dailyProgressRepository;
    @Autowired private CessationPlanRepository cessationPlanRepository;
    @Autowired private SmokingStatusLogRepository smokingStatusLogRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toUserResponse(user);
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRole(request.getRole());
        return toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null) user.setPasswordHash(request.getPassword());
        if (request.getRole() != null) user.setRole(request.getRole());
        return toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    private UserResponse toUserResponse(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getUserID());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public List<MembershipPackageResponse> getAllMembershipPackages() {
        return membershipPackageRepository.findAll().stream().map(this::toMembershipPackageResponse).toList();
    }

    @Override
    public MembershipPackageResponse getMembershipPackageById(Integer id) {
        MembershipPackage pkg = membershipPackageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("MembershipPackage not found"));
        return toMembershipPackageResponse(pkg);
    }

    @Override
    public MembershipPackageResponse createMembershipPackage(MembershipPackageRequest request) {
        MembershipPackage pkg = new MembershipPackage();
        pkg.setPackageName(request.getPackageName());
        pkg.setDescription(request.getDescription());
        pkg.setPrice(request.getPrice());
        // Set other fields as needed
        return toMembershipPackageResponse(membershipPackageRepository.save(pkg));
    }

    @Override
    public MembershipPackageResponse updateMembershipPackage(Integer id, MembershipPackageRequest request) {
        MembershipPackage pkg = membershipPackageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("MembershipPackage not found"));
        pkg.setPackageName(request.getPackageName());
        pkg.setDescription(request.getDescription());
        pkg.setPrice(request.getPrice());
        // Update other fields as needed
        return toMembershipPackageResponse(membershipPackageRepository.save(pkg));
    }

    @Override
    public void deleteMembershipPackage(Integer id) {
        membershipPackageRepository.deleteById(id);
    }

    private MembershipPackageResponse toMembershipPackageResponse(MembershipPackage pkg) {
        MembershipPackageResponse dto = new MembershipPackageResponse();
        dto.setPackageName(pkg.getPackageName());
        dto.setDescription(pkg.getDescription());
        dto.setPrice(pkg.getPrice());
        // Map other fields as needed
        return dto;
    }

    @Override
    public List<Payment> getAllPayments() { return paymentRepository.findAll(); }
    @Override
    public List<BlogPostResponse> getAllBlogPosts() {
        return blogPostRepository.findAll().stream().map(this::toBlogPostResponse).toList();
    }

    @Override
    public BlogPostResponse getBlogPostById(Integer id) {
        BlogPost post = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("BlogPost not found"));
        return toBlogPostResponse(post);
    }

    @Override
    public BlogPostResponse createBlogPost(BlogPostRequest request) {
        BlogPost post = new BlogPost();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setFeaturedImageURL(request.getImageUrl());
        // Set other fields as needed
        return toBlogPostResponse(blogPostRepository.save(post));
    }

    @Override
    public BlogPostResponse updateBlogPost(Integer id, BlogPostRequest request) {
        BlogPost post = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("BlogPost not found"));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setFeaturedImageURL(request.getImageUrl());
        // Update other fields as needed
        return toBlogPostResponse(blogPostRepository.save(post));
    }

    @Override
    public void deleteBlogPost(Integer id) {
        blogPostRepository.deleteById(id);
    }

    private BlogPostResponse toBlogPostResponse(BlogPost post) {
        BlogPostResponse dto = new BlogPostResponse();

        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getFeaturedImageURL());
        return dto;
    }

    @Override
    public BadgeResponse getBadgeById(Integer id) {
        Badge badge = badgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Badge not found"));
        return toBadgeResponse(badge);
    }

    @Override
    public BadgeResponse createBadge(BadgeRequest request) {
        Badge badge = new Badge();
        badge.setBadgeName(request.getBadgeName());
        badge.setIconURL(request.getIconURL());
        badge.setCriteria(request.getCriteria());
        badge.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);
        return toBadgeResponse(badgeRepository.save(badge));
    }

    @Override
    public BadgeResponse updateBadge(Integer id, BadgeRequest request) {
        Badge badge = badgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Badge not found"));
        badge.setBadgeName(request.getBadgeName());
        badge.setIconURL(request.getIconURL());
        badge.setCriteria(request.getCriteria());
        badge.setIsActive(request.getIsActive() != null ? request.getIsActive() : badge.getIsActive());
        return toBadgeResponse(badgeRepository.save(badge));
    }

    @Override
    public void deleteBadge(Integer id) {
        Badge badge = badgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Badge not found"));
        badge.setIsActive(false); // Soft delete: mark as inactive
        badgeRepository.save(badge);
    }

    private BadgeResponse toBadgeResponse(Badge badge) {
        BadgeResponse dto = new BadgeResponse();
        dto.setBadgeID(badge.getBadgeID());
        dto.setBadgeName(badge.getBadgeName());
        dto.setIconURL(badge.getIconURL());
        dto.setCriteria(badge.getCriteria());
        dto.setIsActive(badge.getIsActive());
        return dto;
    }
}
