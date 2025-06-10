package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.MembershipPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembershipPackageRepository extends JpaRepository<MembershipPackage, Long> {
    List<MembershipPackage> findByIsActiveTrue();
}
