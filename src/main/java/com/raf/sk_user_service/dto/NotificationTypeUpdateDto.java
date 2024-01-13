package com.raf.sk_user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTypeUpdateDto {
    private String notificationType, subject, body;
}
