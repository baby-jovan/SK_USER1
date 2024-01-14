package com.raf.sk_user_service.repository;

import com.raf.sk_user_service.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findNotificationByEmailTo(String emailTo);
    List<Notification> findAllByUserId(Long id);
}