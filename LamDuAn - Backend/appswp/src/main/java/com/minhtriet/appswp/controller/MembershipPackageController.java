package com.minhtriet.appswp.controller;

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

    @GetMapping
    public ResponseEntity<List<MembershipPackage>> getAllPackages() {
        return ResponseEntity.ok(membershipPackageService.getAllPackages());
    }

    @GetMapping("/active")
    public ResponseEntity<List<MembershipPackage>> getActivePackages() {
        return ResponseEntity.ok(membershipPackageService.getActivePackages());
    }

    @GetMapping("/{packageId}")
    public ResponseEntity<MembershipPackage> getPackageById(@PathVariable Long packageId) {
        return membershipPackageService.getPackageById(packageId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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
