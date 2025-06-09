package com.minhtriet.appswp.service;

import com.minhtriet.appswp.entity.MembershipPackage;
import com.minhtriet.appswp.entity.Payment;
import com.minhtriet.appswp.entity.User;
import com.minhtriet.appswp.repository.MembershipPackageRepository;
import com.minhtriet.appswp.repository.PaymentRepository;
import com.minhtriet.appswp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipPackageRepository packageRepository;

    /**
     * Xử lý nghiệp vụ mua gói/thanh toán:
     * - Lưu Payment
     * - Cộng dồn ngày nếu user đã có gói còn hạn, hoặc bắt đầu mới nếu đã hết hạn
     * - Update User (CurrentMembershipPackageID, SubscriptionEndDate)
     */
    @Transactional
    public Payment processPackagePurchase(Long userId, Long packageId, String paymentMethod, String transactionId) {
        // 1. Kiểm tra user & package hợp lệ
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<MembershipPackage> pkgOpt = packageRepository.findById(packageId);
        if (userOpt.isEmpty() || pkgOpt.isEmpty()) throw new IllegalArgumentException("User hoặc gói không tồn tại!");

        User user = userOpt.get();
        MembershipPackage membershipPackage = pkgOpt.get();

        // 2. Tính ngày bắt đầu & ngày hết hạn mới
        LocalDate today = LocalDate.now();
        LocalDate newStartDate = today;
        LocalDate newEndDate;

        if (user.getCurrentMembershipPackageId() != null
                && user.getCurrentMembershipPackageId().equals(packageId)
                && user.getSubscriptionEndDate() != null
                && !user.getSubscriptionEndDate().isBefore(today)) {
            // Gói hiện tại vẫn còn hạn, cộng dồn ngày
            newStartDate = today.isAfter(user.getSubscriptionEndDate()) ? today : user.getSubscriptionEndDate().plusDays(1);
            newEndDate = newStartDate.plusDays(membershipPackage.getDurationDays() - 1);
        } else {
            // Mua mới hoặc hết hạn, bắt đầu từ hôm nay
            newStartDate = today;
            newEndDate = newStartDate.plusDays(membershipPackage.getDurationDays() - 1);
        }

        // 3. Lưu Payment
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setMembershipPackage(membershipPackage);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(membershipPackage.getPrice());
        payment.setPaymentMethod(paymentMethod);
        payment.setTransactionId(transactionId);
        payment.setStatus("success"); // hoặc "pending" nếu cần xác nhận
        paymentRepository.save(payment);

        // 4. Update user (gói đang dùng + ngày hết hạn)
        user.setCurrentMembershipPackageId(membershipPackage.getPackageId());
        user.setSubscriptionEndDate(newEndDate);
        userRepository.save(user);

        return payment;
    }
}
