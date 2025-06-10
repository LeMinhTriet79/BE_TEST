package com.minhtriet.appswp.dto;

import lombok.Data;

/**
 * DTO nhận dữ liệu thanh toán từ FE
 */
@Data
public class PaymentRequestDTO {
    private long userId;
    private long packageId;
    private double amount;
    private String paymentMethod;     // (Momo, ZaloPay, Chuyển khoản,...)
    private String transactionId;     // (Mã giao dịch từ bên ngoài, nếu có)
}
