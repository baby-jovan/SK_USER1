package com.raf.sk_user_service.repository;

import com.raf.sk_user_service.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    NotificationType findNotificationTypeByNotificationType(String notificationType);
}
