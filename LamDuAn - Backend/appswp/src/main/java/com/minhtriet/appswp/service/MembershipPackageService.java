package com.minhtriet.appswp.service;

import com.minhtriet.appswp.dto.MembershipPackageDTO;
import com.minhtriet.appswp.entity.MembershipPackage;
import com.minhtriet.appswp.repository.MembershipPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service xử lý nghiệp vụ Membership Package (gói thành viên).
 * Chứa các logic trung gian giữa controller và repository.
 */
@Service
public class MembershipPackageService {

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;

    // Lấy tất cả các gói (entity)
    public List<MembershipPackage> getAllPackages() {
        return membershipPackageRepository.findAll();
    }

    // Lấy các gói đang active (entity)
    public List<MembershipPackage> getActivePackages() {
        return membershipPackageRepository.findByIsActiveTrue();
    }

    // Lấy chi tiết gói theo id (entity)
    public Optional<MembershipPackage> getPackageById(Long packageId) {
        return membershipPackageRepository.findById(packageId);
    }

    // Tạo mới gói
    public MembershipPackage createPackage(MembershipPackage pkg) {
        return membershipPackageRepository.save(pkg);
    }

    // Sửa gói
    public Optional<MembershipPackage> updatePackage(Long packageId, MembershipPackage updatedPkg) {
        return membershipPackageRepository.findById(packageId).map(pkg -> {
            pkg.setPackageName(updatedPkg.getPackageName());
            pkg.setPrice(updatedPkg.getPrice());
            pkg.setDurationDays(updatedPkg.getDurationDays());
            pkg.setDescription(updatedPkg.getDescription());
            pkg.setActive(updatedPkg.isActive());
            return membershipPackageRepository.save(pkg);
        });
    }

    // Xóa gói
    public boolean deletePackage(Long packageId) {
        return membershipPackageRepository.findById(packageId)
                .map(pkg -> {
                    membershipPackageRepository.delete(pkg);
                    return true;
                }).orElse(false);
    }

    // =========================
    // ==== DTO VERSION BELOW ===
    // =========================

    // Convert entity -> DTO
    private MembershipPackageDTO toDTO(MembershipPackage entity) {
        MembershipPackageDTO dto = new MembershipPackageDTO();
        dto.setPackageId(entity.getPackageId());
        dto.setPackageName(entity.getPackageName());
        dto.setPrice(entity.getPrice());
        dto.setDurationDays(entity.getDurationDays());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.isActive());
        return dto;
    }

    // Lấy tất cả các gói (DTO)
    public List<MembershipPackageDTO> getAllPackagesDTO() {
        return membershipPackageRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy các gói đang active (DTO)
    public List<MembershipPackageDTO> getActivePackagesDTO() {
        return membershipPackageRepository.findByIsActiveTrue()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết gói theo id (DTO)
    public Optional<MembershipPackageDTO> getPackageByIdDTO(Long packageId) {
        return membershipPackageRepository.findById(packageId)
                .map(this::toDTO);
    }
}
