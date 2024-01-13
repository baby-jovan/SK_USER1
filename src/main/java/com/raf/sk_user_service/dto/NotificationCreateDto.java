package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationCreateDto {
    private Long clientId;
    private String subject, emailTo, body;
    private NotificationType notificationType;
}
