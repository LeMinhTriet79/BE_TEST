package com.example.swp.dto.request;

import lombok.Data;

@Data
public class MembershipPackageRequest {
    private String packageName;
    private String description;
    private Double price;
    // Thêm các trường khác nếu cần
}

