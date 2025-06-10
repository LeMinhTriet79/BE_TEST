package com.minhtriet.appswp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.entity.VerificationToken;
import com.minhtriet.appswp.entity.Coach;
import com.minhtriet.appswp.entity.MembershipPackage;
import com.minhtriet.appswp.repository.UserRepository;
import com.minhtriet.appswp.repository.VerificationTokenRepository;
import com.minhtriet.appswp.repository.CoachRepository;
import com.minhtriet.appswp.repository.MembershipPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;

    // ========================= ĐĂNG KÝ OTP 2 BƯỚC =========================

    /**
     * BƯỚC 1: Gửi OTP về email, lưu user tạm vào VerificationToken (type = REGISTER_OTP)
     */
    public void registerUserWithOtp(User user) {
        // 1. Check username/email đã tồn tại trong DB chưa
        if (isUsernameExists(user.getUsername()) || isEmailExists(user.getEmail())) {
            throw new IllegalArgumentException("Username or Email already exists!");
        }
        user.setEnabled(false); // Đánh dấu chưa xác thực
        user.setRegistrationDate(LocalDateTime.now());

        // 2. Serialize toàn bộ user thành JSON
        String userJson;
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot serialize user data", e);
        }

        // 3. Sinh OTP (4 số)
        String otp = generateOtp();

        // 4. Xóa OTP cũ nếu có
        tokenRepository.findByEmailAndType(user.getEmail(), "REGISTER_OTP")
                .ifPresent(tokenRepository::delete);

        // 5. Lưu vào bảng VerificationToken
        VerificationToken vt = new VerificationToken();
        vt.setToken(otp); // Lưu OTP
        vt.setEmail(user.getEmail());
        vt.setUserInfo(userJson); // Lưu tạm user
        vt.setExpiryDate(LocalDateTime.now().plusMinutes(10)); // OTP sống 10 phút
        vt.setType("REGISTER_OTP");
        tokenRepository.save(vt);

        // 6. Gửi OTP qua email
        emailService.sendOtpResetPassword(user.getEmail(), otp); // Nếu muốn custom nội dung, tạo hàm riêng cho đăng ký
    }

    /**
     * BƯỚC 2: Xác thực OTP và lưu user vào bảng User
     */
    public boolean verifyOtpAndRegister(String email, String otp) {
        Optional<VerificationToken> vtOpt = tokenRepository.findByEmailAndType(email, "REGISTER_OTP");
        if (vtOpt.isEmpty()) return false;
        VerificationToken vt = vtOpt.get();

        // Kiểm tra OTP đúng và chưa hết hạn
        if (!vt.getToken().equals(otp) || vt.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(vt);
            return false;
        }

        // Deserialize user từ userInfo
        try {
            User user = objectMapper.readValue(vt.getUserInfo(), User.class);

            // Check lại email có tồn tại không (tránh double register)
            if (userRepository.existsByEmail(user.getEmail())) {
                tokenRepository.delete(vt);
                return false;
            }

            user.setEnabled(true); // Đã xác thực OTP
            userRepository.save(user);
            tokenRepository.delete(vt);
            return true;
        } catch (Exception e) {
            tokenRepository.delete(vt);
            return false;
        }
    }

    // ======================= OTP cho QUÊN MẬT KHẨU ==========================

    /**
     * Sinh ra OTP 4 số ngẫu nhiên dưới dạng String.
     */
    private String generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Số từ 1000 - 9999
        return String.valueOf(otp);
    }

    /**
     * Gửi OTP về email để xác thực đổi mật khẩu.
     * - Lưu OTP vào VerificationToken (type = PASSWORD_RESET_OTP)
     * - Lưu tạm mật khẩu mới vào userInfo
     */
    public boolean sendPasswordResetOtp(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) return false;

        // 1. Sinh OTP
        String otp = generateOtp();

        // 2. Xóa OTP cũ (nếu có)
        tokenRepository.findByEmailAndType(email, "PASSWORD_RESET_OTP")
                .ifPresent(tokenRepository::delete);

        // 3. Lưu VerificationToken
        VerificationToken vt = new VerificationToken();
        vt.setToken(otp);
        vt.setEmail(email);
        vt.setUserInfo(newPassword);
        vt.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        vt.setType("PASSWORD_RESET_OTP");
        tokenRepository.save(vt);

        // 4. Gửi OTP qua email
        emailService.sendOtpResetPassword(email, otp);
        return true;
    }

    /**
     * Kiểm tra OTP đổi mật khẩu, nếu đúng thì đổi mật khẩu mới cho user.
     */
    public boolean verifyOtpAndResetPassword(String email, String otp) {
        Optional<VerificationToken> vtOpt = tokenRepository.findByEmailAndType(email, "PASSWORD_RESET_OTP");
        if (vtOpt.isEmpty()) return false;
        VerificationToken vt = vtOpt.get();

        // Kiểm tra OTP đúng & chưa hết hạn
        if (!vt.getToken().equals(otp) || vt.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(vt);
            return false;
        }

        // Đổi mật khẩu mới (userInfo lưu tạm mật khẩu mới)
        User user = userRepository.findByEmail(email);
        if (user == null) {
            tokenRepository.delete(vt);
            return false;
        }

        user.setPasswordHash(vt.getUserInfo());
        userRepository.save(user);

        tokenRepository.delete(vt); // Xóa OTP đã dùng
        return true;
    }

    // ================== CRUD & CHECK ==================

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(Long id) { return userRepository.findById(id).orElse(null); }

    /**
     * Tạo user mới, tự động dùng email làm username nếu username chưa được nhập.
     */
    public User createNewUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            user.setUsername(user.getEmail());
        }
        return userRepository.save(user);
    }

    /**
     * Cập nhật user theo id, đồng bộ mapping với entity mới (KHÔNG dùng trường id primitive cho coach/package)
     */
    public User updateUserById(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    if (updatedUser.getUsername() != null && !updatedUser.getUsername().trim().isEmpty()) {
                        user.setUsername(updatedUser.getUsername());
                    }
                    if (updatedUser.getEmail() != null && !updatedUser.getEmail().trim().isEmpty()) {
                        user.setEmail(updatedUser.getEmail());
                    }
                    if (updatedUser.getFullName() != null) {
                        user.setFullName(updatedUser.getFullName());
                    }
                    if (updatedUser.getProfilePictureUrl() != null) {
                        user.setProfilePictureUrl(updatedUser.getProfilePictureUrl());
                    }
                    // Update MembershipPackage object nếu có
                    if (updatedUser.getCurrentMembershipPackage() != null) {
                        MembershipPackage mp = membershipPackageRepository.findById(
                                updatedUser.getCurrentMembershipPackage().getPackageId()
                        ).orElse(null);
                        user.setCurrentMembershipPackage(mp);
                    }
                    if (updatedUser.getSubscriptionEndDate() != null) {
                        user.setSubscriptionEndDate(updatedUser.getSubscriptionEndDate());
                    }
                    // Update Coach object nếu có
                    if (updatedUser.getCoach() != null) {
                        Coach coach = coachRepository.findById(
                                updatedUser.getCoach().getCoachId()
                        ).orElse(null);
                        user.setCoach(coach);
                    }
                    if (updatedUser.getRole() != null && !updatedUser.getRole().trim().isEmpty()) {
                        user.setRole(updatedUser.getRole());
                    }
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public boolean deleteUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void updateLastLoginDate(Long userId) {
        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.setLastLoginDate(LocalDateTime.now());
                    userRepository.save(user);
                });
    }

    public boolean updatePassword(Long userId, String newPasswordHash) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setPasswordHash(newPasswordHash);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Lấy tất cả user đã được gán coach (coach != null)
     */
    public List<User> getUsersWithCoach() {
        return userRepository.findByCoachIsNotNull();
    }

    /**
     * Lấy tất cả user thuộc coach cụ thể (theo Coach object)
     */
    public List<User> getUsersByCoach(Coach coach) {
        return userRepository.findByCoach(coach);
    }
}
