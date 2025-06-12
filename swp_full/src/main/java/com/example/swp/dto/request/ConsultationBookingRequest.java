package com.example.swp.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultationBookingRequest {
    private Integer coachId;
    private LocalDateTime scheduledTime;
    private String notes;
}

