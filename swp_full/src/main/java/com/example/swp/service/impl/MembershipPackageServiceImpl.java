package com.example.swp.service.impl;

import com.example.swp.dto.request.MembershipPackageRequest;
import com.example.swp.dto.response.MembershipPackageResponse;
import com.example.swp.dto.MembershipPackageMapper;
import com.example.swp.entity.MembershipPackage;
import com.example.swp.repository.MembershipPackageRepository;
import com.example.swp.service.MembershipPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MembershipPackageServiceImpl implements MembershipPackageService {

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;

    @Override
    public MembershipPackageResponse create(MembershipPackageRequest request) {
        MembershipPackage entity = MembershipPackageMapper.toEntity(request);
        MembershipPackage saved = membershipPackageRepository.save(entity);
        return MembershipPackageMapper.toResponse(saved);
    }

    @Override
    public MembershipPackageResponse update(Integer id, MembershipPackageRequest request) {
        MembershipPackage existing = membershipPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MembershipPackage not found"));
        MembershipPackageMapper.updateEntity(existing, request);
        MembershipPackage saved = membershipPackageRepository.save(existing);
        return MembershipPackageMapper.toResponse(saved);
    }

    @Override
    public void softDelete(Integer id) {
        MembershipPackage entity = membershipPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MembershipPackage not found"));
        entity.setDeleted(true);
        membershipPackageRepository.save(entity);
    }

    @Override
    public MembershipPackageResponse getById(Integer id){
        return membershipPackageRepository.findById(id)
                .map(MembershipPackageMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("MembershipPackage not found"));
     }

    @Override
    public List<MembershipPackageResponse> getAll() {
        List<MembershipPackage> membershipPackage = membershipPackageRepository.findAll();
        List<MembershipPackageResponse> response = membershipPackage.stream()
                .map(MembershipPackageMapper::toResponse)
                .toList();
        return response;
    }


}
