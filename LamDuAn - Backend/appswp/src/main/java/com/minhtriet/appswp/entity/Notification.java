package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private long notificationId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "MessageType", nullable = false)
    private String messageType;

    @Column(name = "MessageContent", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String messageContent;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "IsRead", nullable = false)
    private boolean isRead;

    @Column(name = "ScheduledSendTime")
    private LocalDateTime scheduledSendTime;

    @Column(name = "RelatedEntityID")
    private Long relatedEntityId;

    @Column(name = "RelatedEntityType")
    private String relatedEntityType;
} 