package com.minhtriet.appswp.service;

import com.minhtriet.appswp.entity.MembershipPackage;
import com.minhtriet.appswp.repository.MembershipPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service xử lý nghiệp vụ Membership Package (gói thành viên).
 * Chứa các logic trung gian giữa controller và repository.
 */
@Service
public class MembershipPackageService {

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;

    // Lấy tất cả các gói
    public List<MembershipPackage> getAllPackages() {
        return membershipPackageRepository.findAll();
    }

    // Lấy các gói đang active
    public List<MembershipPackage> getActivePackages() {
        return membershipPackageRepository.findByIsActiveTrue();
    }

    // Lấy chi tiết gói theo id
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
}
