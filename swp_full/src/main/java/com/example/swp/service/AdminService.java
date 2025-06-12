package com.example.swp.service;

import com.example.swp.dto.response.BlogPostResponse;
import com.example.swp.dto.response.BadgeResponse;
import com.example.swp.dto.response.UserResponse;
import com.example.swp.dto.response.MembershipPackageResponse;
import com.example.swp.entity.*;
import com.example.swp.dto.request.BadgeRequest;
import com.example.swp.dto.request.BlogPostRequest;
import com.example.swp.dto.request.UserRequest;
import com.example.swp.dto.request.MembershipPackageRequest;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Integer id);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Integer id, UserRequest request);
    void deleteUser(Integer id);

    List<MembershipPackageResponse> getAllMembershipPackages();
    MembershipPackageResponse getMembershipPackageById(Integer id);
    MembershipPackageResponse createMembershipPackage(MembershipPackageRequest request);
    MembershipPackageResponse updateMembershipPackage(Integer id, MembershipPackageRequest request);
    void deleteMembershipPackage(Integer id);
/// ///////////
    List<Payment> getAllPayments();
    List<BlogPostResponse> getAllBlogPosts();

/// ////////////////
    BlogPostResponse getBlogPostById(Integer id);
    BlogPostResponse createBlogPost(BlogPostRequest request);
    BlogPostResponse updateBlogPost(Integer id, BlogPostRequest request);
    void deleteBlogPost(Integer id);


    /// ///////////////
    BadgeResponse getBadgeById(Integer id);
    BadgeResponse createBadge(BadgeRequest request);
    BadgeResponse updateBadge(Integer id, BadgeRequest request);
    void deleteBadge(Integer id);
}
