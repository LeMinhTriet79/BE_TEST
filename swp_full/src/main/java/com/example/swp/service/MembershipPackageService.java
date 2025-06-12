package com.example.swp.service;

import com.example.swp.dto.request.MembershipPackageRequest;
import com.example.swp.dto.response.MembershipPackageResponse;

import java.util.List;

public interface MembershipPackageService {
    MembershipPackageResponse create(MembershipPackageRequest request);
    MembershipPackageResponse update(Integer id, MembershipPackageRequest request);
    MembershipPackageResponse getById(Integer id);
    List<MembershipPackageResponse> getAll();
    void softDelete(Integer id);
}
