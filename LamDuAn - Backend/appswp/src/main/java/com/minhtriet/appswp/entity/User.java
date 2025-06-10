package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity đại diện cho bảng Users trong CSDL.
 * Lưu thông tin người dùng, gói thành viên hiện tại, coach, trạng thái tài khoản,...
 */
@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /** ID tự tăng, khóa chính. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private long userId;

    /** Tên đăng nhập, duy nhất, hỗ trợ Unicode. */
    @Nationalized
    @Column(name = "Username", nullable = false, unique = true, length = 255)
    private String username;

    /** Mật khẩu đã hash (bắt buộc). */
    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    /** Email duy nhất (bắt buộc, dùng để xác thực hoặc lấy lại mật khẩu). */
    @Column(name = "Email", nullable = false, unique = true, length = 255)
    private String email;

    /** Họ và tên (Unicode, có thể null). */
    @Nationalized
    @Column(name = "FullName", length = 255)
    private String fullName;

    /** Thời điểm đăng ký tài khoản (bắt buộc). */
    @Column(name = "RegistrationDate", nullable = false)
    private LocalDateTime registrationDate;

    /** Thời điểm đăng nhập lần cuối (có thể null). */
    @Column(name = "LastLoginDate")
    private LocalDateTime lastLoginDate;

    /** Link ảnh đại diện (có thể null). */
    @Column(name = "ProfilePictureURL", length = 255)
    private String profilePictureUrl;

    /**
     * Gói thành viên hiện tại mà user đang sở hữu (mapping với MembershipPackages).
     * Nếu user chưa đăng ký gói nào, trường này có thể null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CurrentMembershipPackageID")
    private MembershipPackage currentMembershipPackage;

    /** Ngày hết hạn gói thành viên hiện tại (có thể null). */
    @Column(name = "SubscriptionEndDate")
    private LocalDate subscriptionEndDate;

    /**
     * Coach mà user này liên kết (nếu có).
     * Có thể null nếu user không có coach riêng.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CoachID")
    private Coach coach;

    /** Vai trò tài khoản: mặc định là "member". Có thể là "admin", "coach",... */
    @Column(name = "Role", nullable = false, length = 255)
    private String role = "member";

    /** Trạng thái xác thực tài khoản (đã xác thực email hay chưa). */
    @Column(name = "Enabled", nullable = false)
    private boolean enabled = false;

    // ==========================================
    // Các quan hệ phụ có thể bổ sung nếu cần dùng:
    // ==========================================
    // @OneToMany(mappedBy = "user")
    // private List<Payment> payments;

    // @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    // private Coach coachProfile;

    // =====================
    // Tự động set dữ liệu khi tạo mới
    // =====================
    @PrePersist
    protected void onCreate() {
        if (registrationDate == null) {
            registrationDate = LocalDateTime.now();
        }
        if (role == null || role.trim().isEmpty()) {
            role = "member";
        }
        // Nếu muốn username mặc định là email khi không nhập username:
        // if (username == null || username.trim().isEmpty()) {
        //     username = email;
        // }
    }
}
