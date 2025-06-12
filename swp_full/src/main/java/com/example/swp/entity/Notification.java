package com.example.swp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String messageType;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String messageContent;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean isRead = false;

    private LocalDateTime scheduledSendTime;
    private Integer relatedEntityID;
    private String relatedEntityType;
}
