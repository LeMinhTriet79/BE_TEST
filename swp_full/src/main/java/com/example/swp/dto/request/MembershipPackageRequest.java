package com.example.swp.dto.request;

import lombok.Data;

@Data
public class MembershipPackageRequest {
    private String packageName;
    private Double price;
    private Integer durationDays;
    private String description;
    private Boolean isActive;
}

