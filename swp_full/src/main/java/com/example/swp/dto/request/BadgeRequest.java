package com.example.swp.dto.request;

import lombok.Data;

@Data
public class BadgeRequest {
    private String badgeName;
    private String iconURL;
    private String criteria;
    private Boolean isActive;
}

