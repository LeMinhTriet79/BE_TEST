package com.minhtriet.appswp.dto;

import lombok.Data;

@Data
public class MembershipPackageDTO {
    private long packageId;
    private String packageName;
    private double price;
    private int durationDays;
    private String description;
    private boolean isActive;
}
