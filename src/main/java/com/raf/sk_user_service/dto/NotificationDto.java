package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationDto {
    private String emailTo, subject, body;
    private NotificationType notificationType;

    public NotificationDto(String emailTo, String subject, String body, NotificationType notificationType) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.body = body;
        this.notificationType = notificationType;
    }

    public NotificationDto() {
    }
}
