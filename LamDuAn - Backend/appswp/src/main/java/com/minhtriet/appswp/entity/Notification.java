package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "MessageType", nullable = false, length = 255)
    private String messageType;

    @Column(name = "MessageContent", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String messageContent;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "IsRead", nullable = false)
    private boolean isRead = false;

    @Column(name = "ScheduledSendTime")
    private LocalDateTime scheduledSendTime;

    @Column(name = "RelatedEntityID")
    private Long relatedEntityId;

    @Column(name = "RelatedEntityType", length = 255)
    private String relatedEntityType;
}
