package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class NotificationCreateDto {
    private Long clientId;
    private String emailTo;
    private NotificationType notificationType;
    private Date createdDate;
    private Date updatedDate;
}
