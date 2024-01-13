package com.raf.sk_user_service.mapper;

import com.raf.sk_user_service.domain.Notification;
import com.raf.sk_user_service.dto.NotificationCreateDto;
import com.raf.sk_user_service.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDto notificationToNotificationDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationType(notification.getNotificationType());
        notificationDto.setSubject(notification.getSubject());
        notificationDto.setEmailTo(notificationDto.getEmailTo());
        notificationDto.setBody(notificationDto.getBody());

        return notificationDto;
    }

    public Notification notificationDtoToNotification(NotificationCreateDto notificationCreateDto){
        Notification notification = new Notification();
        notification.setNotificationType(notificationCreateDto.getNotificationType());
        notification.setSubject(notificationCreateDto.getSubject());
        notification.setUserId(notificationCreateDto.getClientId());
        notification.setBody(notificationCreateDto.getBody());
        notification.setEmailTo(notificationCreateDto.getEmailTo());
        return notification;
    }
}
