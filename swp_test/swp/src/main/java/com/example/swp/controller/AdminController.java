package com.example.swp.controller;

import com.example.swp.dto.request.BlogPostRequest;
import com.example.swp.dto.response.BlogPostResponse;
import com.example.swp.entity.*;
import com.example.swp.service.AdminService;
import com.example.swp.dto.request.BadgeRequest;
import com.example.swp.dto.response.BadgeResponse;
import com.example.swp.dto.request.UserRequest;
import com.example.swp.dto.response.UserResponse;
import com.example.swp.dto.request.MembershipPackageRequest;
import com.example.swp.dto.response.MembershipPackageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired private AdminService adminService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() { return adminService.getAllUsers(); }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Integer id) { return adminService.getUserById(id); }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return adminService.createUser(request);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @RequestBody UserRequest request) {
        return adminService.updateUser(id, request);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        adminService.deleteUser(id);
    }

    @GetMapping("/membership-packages")
    public List<MembershipPackageResponse> getAllMembershipPackages() { return adminService.getAllMembershipPackages(); }

    @GetMapping("/membership-packages/{id}")
    public MembershipPackageResponse getMembershipPackageById(@PathVariable Integer id) {
        return adminService.getMembershipPackageById(id);
    }

    @PostMapping("/membership-packages")
    public MembershipPackageResponse createMembershipPackage(@RequestBody MembershipPackageRequest request) {
        return adminService.createMembershipPackage(request);
    }

    @PutMapping("/membership-packages/{id}")
    public MembershipPackageResponse updateMembershipPackage(@PathVariable Integer id, @RequestBody MembershipPackageRequest request) {
        return adminService.updateMembershipPackage(id, request);
    }

    @DeleteMapping("/membership-packages/{id}")
    public void deleteMembershipPackage(@PathVariable Integer id) {
        adminService.deleteMembershipPackage(id);
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() { return adminService.getAllPayments(); }

    @GetMapping("/blog-posts")
    public List<BlogPostResponse> getAllBlogPosts() { return adminService.getAllBlogPosts(); }

    @GetMapping("/blog-posts/{id}")
    public BlogPostResponse getBlogPostById(@PathVariable Integer id) {
        return adminService.getBlogPostById(id);
    }

    @PostMapping("/blog-posts")
    public BlogPostResponse createBlogPost(@RequestBody BlogPostRequest request) {
        return adminService.createBlogPost(request);
    }

    @PutMapping("/blog-posts/{id}")
    public BlogPostResponse updateBlogPost(@PathVariable Integer id, @RequestBody BlogPostRequest request) {
        return adminService.updateBlogPost(id, request);
    }

    @DeleteMapping("/blog-posts/{id}")
    public void deleteBlogPost(@PathVariable Integer id) {
        adminService.deleteBlogPost(id);
    }



    @GetMapping("/badges/{id}")
    public BadgeResponse getBadgeById(@PathVariable Integer id) {
        return adminService.getBadgeById(id);
    }

    @PostMapping("/badges")
    public BadgeResponse createBadge(@RequestBody BadgeRequest request) {
        return adminService.createBadge(request);
    }

    @PutMapping("/badges/{id}")
    public BadgeResponse updateBadge(@PathVariable Integer id, @RequestBody BadgeRequest request) {
        return adminService.updateBadge(id, request);
    }
    @DeleteMapping("/badges/{id}")
    public void deleteBadge(@PathVariable Integer id) {
        adminService.deleteBadge(id);
    }
}
