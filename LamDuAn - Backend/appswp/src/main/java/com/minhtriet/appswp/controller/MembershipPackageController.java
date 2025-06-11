package com.minhtriet.appswp.controller;

import com.minhtriet.appswp.dto.MembershipPackageDTO;
import com.minhtriet.appswp.entity.MembershipPackage;
import com.minhtriet.appswp.service.MembershipPackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-packages")
@CrossOrigin
public class MembershipPackageController {

    @Autowired
    private MembershipPackageService membershipPackageService;

    // GET: /api/membership-packages (trả về DTO)
    @GetMapping
    public ResponseEntity<List<MembershipPackageDTO>> getAllPackages() {
        return ResponseEntity.ok(membershipPackageService.getAllPackagesDTO());
    }

    // GET: /api/membership-packages/active (trả về DTO)
    @GetMapping("/active")
    public ResponseEntity<List<MembershipPackageDTO>> getActivePackages() {
        return ResponseEntity.ok(membershipPackageService.getActivePackagesDTO());
    }

    // GET: /api/membership-packages/{packageId} (trả về DTO)
    @GetMapping("/{packageId}")
    public ResponseEntity<MembershipPackageDTO> getPackageById(@PathVariable Long packageId) {
        return membershipPackageService.getPackageByIdDTO(packageId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST/PUT vẫn nhận/trả entity cho admin quản lý, không ảnh hưởng tới FE lấy danh sách
    @PostMapping
    public ResponseEntity<MembershipPackage> createPackage(@Valid @RequestBody MembershipPackage membershipPackage) {
        MembershipPackage saved = membershipPackageService.createPackage(membershipPackage);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{packageId}")
    public ResponseEntity<MembershipPackage> updatePackage(
            @PathVariable Long packageId,
            @Valid @RequestBody MembershipPackage updatedPackage) {
        return membershipPackageService.updatePackage(packageId, updatedPackage)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long packageId) {
        boolean deleted = membershipPackageService.deletePackage(packageId);
        if (deleted) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
