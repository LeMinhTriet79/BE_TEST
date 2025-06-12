package com.example.swp.dto.response;

import lombok.Data;

@Data
public class MembershipPackageResponse {
    private Integer id;
    private String packageName;
    private String description;
    private Double price;
    // Thêm các trường khác nếu cần
}

