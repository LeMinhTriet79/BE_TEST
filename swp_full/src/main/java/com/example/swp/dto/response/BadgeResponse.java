package com.example.swp.dto.response;

import lombok.Data;

@Data
public class BadgeResponse {
    private Integer badgeID;
    private String badgeName;
    private String iconURL;
    private String criteria;
    private Boolean isActive;
}

