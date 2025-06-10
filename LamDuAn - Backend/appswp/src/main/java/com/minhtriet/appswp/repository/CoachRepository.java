package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Coach;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository thao tác với bảng Coaches.
 * Cho phép truy vấn, tìm kiếm, và thao tác CRUD cho coach.
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    // Tìm coach theo user (nếu cần truy vấn ngược)
    Coach findByUser(User user);

    // Tìm coach theo tên (search, không phân biệt hoa thường, Unicode)
    List<Coach> findByFullNameContainingIgnoreCase(String fullName);

    // Tìm coach theo trạng thái (isActive)
    List<Coach> findByIsActiveTrue();

    // Có thể bổ sung: lấy toàn bộ coach đang active và có chuyên môn
    List<Coach> findByIsActiveTrueAndSpecializationContainingIgnoreCase(String specialization);

    // Nếu muốn kiểm tra coach đã tồn tại theo user
    boolean existsByUser(User user);
}
