package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.NotificationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Setter
@Getter
public class NotificationDto {
    private String emailTo;
    private Long clientId;
    private NotificationType notificationType;
    private Date createdDate;
    private Date updatedDate;

    public NotificationDto(String emailTo, NotificationType notificationType) {
        this.emailTo = emailTo;
        this.notificationType = notificationType;
    }

    public NotificationDto() {
    }
}
