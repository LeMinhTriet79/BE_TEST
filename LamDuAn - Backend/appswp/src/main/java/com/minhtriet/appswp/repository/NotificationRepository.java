package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Notification;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
    List<Notification> findByUserAndIsReadFalse(User user);
}
