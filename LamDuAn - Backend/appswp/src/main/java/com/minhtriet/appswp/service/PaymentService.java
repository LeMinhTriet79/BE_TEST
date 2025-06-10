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
        // Lấy User và Package từ DB
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPackage membershipPackage = membershipPackageRepository.findById(dto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));

        // Tạo record payment
        Payment payment = Payment.builder()
                .user(user)
                .membershipPackage(membershipPackage)
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .transactionId(dto.getTransactionId())
                .status("pending")
                .paymentDate(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        // Giả lập luôn việc thanh toán thành công (tùy nghiệp vụ tích hợp real payment, bạn sẽ xác nhận sau)
        // Nếu thành công, update User
        payment.setStatus("success");
        paymentRepository.save(payment);

        // Cập nhật User: gán gói thành viên mới + thời hạn
        user.setCurrentMembershipPackage(membershipPackage);

        // Tính toán ngày hết hạn mới (today + durationDays của package)
        LocalDate endDate = LocalDate.now().plusDays(membershipPackage.getDurationDays());
        user.setSubscriptionEndDate(endDate);

        userRepository.save(user);

        return payment;
    }
}
