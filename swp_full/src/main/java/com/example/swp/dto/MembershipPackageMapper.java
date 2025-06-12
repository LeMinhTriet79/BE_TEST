package com.example.swp.dto;

import com.example.swp.dto.request.MembershipPackageRequest;
import com.example.swp.dto.response.MembershipPackageResponse;
import com.example.swp.entity.MembershipPackage;

public class MembershipPackageMapper {
    public static MembershipPackage toEntity(MembershipPackageRequest dto) {
        MembershipPackage entity = new MembershipPackage();
        entity.setPackageName(dto.getPackageName());
        entity.setPrice(dto.getPrice());
        entity.setDurationDays(dto.getDurationDays());
        entity.setDescription(dto.getDescription());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }

    public static MembershipPackageResponse toResponse(MembershipPackage entity) {
        MembershipPackageResponse dto = new MembershipPackageResponse();
        dto.setPackageName(entity.getPackageName());
        dto.setPrice(entity.getPrice());
        dto.setDurationDays(entity.getDurationDays());
        dto.setDescription(entity.getDescription());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }

    public static void updateEntity(MembershipPackage entity, MembershipPackageRequest dto) {
        entity.setPackageName(dto.getPackageName());
        entity.setPrice(dto.getPrice());
        entity.setDurationDays(dto.getDurationDays());
        entity.setDescription(dto.getDescription());
        entity.setIsActive(dto.getIsActive());
    }
}

