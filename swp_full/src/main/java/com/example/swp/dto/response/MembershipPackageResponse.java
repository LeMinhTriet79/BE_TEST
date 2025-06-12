package com.example.swp.dto.response;

import lombok.Data;

@Data
public class MembershipPackageResponse {
    private String packageName;
    private Double price;
    private Integer durationDays;
    private String description;
    private Boolean isActive;
}

