package com.minhtriet.appswp.service;

import com.minhtriet.appswp.dto.PaymentRequestDTO;
import com.minhtriet.appswp.entity.*;
import com.minhtriet.appswp.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;

    @Transactional
    public Payment createPayment(PaymentRequestDTO dto) {
        // 1. Lấy User và Package từ DB
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        MembershipPackage membershipPackage = membershipPackageRepository.findById(dto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Gói thành viên không tồn tại"));

        // 2. Tạo bản ghi Payment mới, set status luôn là "success"
        Payment payment = Payment.builder()
                .user(user)
                .membershipPackage(membershipPackage)
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .transactionId(dto.getTransactionId())
                .status("success") // đã thanh toán thành công
                .paymentDate(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);

        // 3. Gán gói thành viên mới cho User
        user.setCurrentMembershipPackage(membershipPackage);

        // 4. Tính ngày hết hạn mới = hôm nay + durationDays của package
        LocalDate endDate = LocalDate.now().plusDays(membershipPackage.getDurationDays());
        user.setSubscriptionEndDate(endDate);

        userRepository.save(user);

        return payment;
    }
}
