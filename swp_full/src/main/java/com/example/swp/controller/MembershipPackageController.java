package com.example.swp.controller;

import com.example.swp.service.MembershipPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.swp.dto.request.MembershipPackageRequest;
import com.example.swp.dto.response.MembershipPackageResponse;

import java.util.List;

@RestController
@RequestMapping("/membership-packages")
public class MembershipPackageController {

    @Autowired
    private MembershipPackageService membershipPackageService;

    @PostMapping
    public MembershipPackageResponse create(@RequestBody MembershipPackageRequest request) {
        return membershipPackageService.create(request);
    }

    @PutMapping("/{id}")
    public MembershipPackageResponse update(@PathVariable Integer id, @RequestBody MembershipPackageRequest request) {
        return membershipPackageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        membershipPackageService.softDelete(id);
    }

    @GetMapping("/{id}")
    public MembershipPackageResponse getById(@PathVariable Integer id) {
        return membershipPackageService.getById(id);
    }

    @GetMapping
    public List<MembershipPackageResponse> getAll() {
        return membershipPackageService.getAll();
    }
}
