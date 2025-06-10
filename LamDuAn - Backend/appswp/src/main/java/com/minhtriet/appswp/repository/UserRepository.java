package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository thao tác với bảng Users.
 * Tích hợp nhiều hàm tìm kiếm phục vụ nghiệp vụ đăng nhập, phân quyền, tìm theo coach,...
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm user theo username (cho đăng nhập)
    User findByUsername(String username);

    // Tìm user theo email (cho đăng nhập/đăng ký)
    User findByEmail(String email);

    // Lấy users theo role (phân quyền: member, admin, coach)
    List<User> findByRole(String role);

    // Kiểm tra username đã tồn tại (cho đăng ký)
    boolean existsByUsername(String username);

    // Kiểm tra email đã tồn tại (cho đăng ký)
    boolean existsByEmail(String email);

    // Lấy users có coach (coach object không null)
    List<User> findByCoachIsNotNull();

    // Lấy users theo coach cụ thể
    List<User> findByCoach(Coach coach);

    // Tìm user theo username hoặc email (cho đăng nhập linh hoạt)
    User findByUsernameOrEmail(String username, String email);

    // Có thể bổ sung hàm lấy users đang enabled
    // List<User> findByEnabledTrue();
}
