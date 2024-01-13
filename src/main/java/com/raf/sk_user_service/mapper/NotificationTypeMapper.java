package com.raf.sk_user_service.mapper;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.domain.NotificationType;
import com.raf.sk_user_service.dto.ClientCreateDto;
import com.raf.sk_user_service.dto.ClientDto;
import com.raf.sk_user_service.dto.NotificationTypeDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {
    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType){
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        notificationTypeDto.setId(notificationType.getId());
        notificationTypeDto.setNotificationType(notificationType.getNotificationType());
        notificationTypeDto.setBody(notificationType.getBody());
        notificationTypeDto.setSubject(notificationType.getSubject());
        return notificationTypeDto;
    }

    public NotificationType notificationTypeDtoToNotificationType(NotificationTypeDto notificationTypeDto){
        NotificationType notificationType = new NotificationType();
        notificationType.setNotificationType(notificationTypeDto.getNotificationType());
        notificationType.setBody(notificationTypeDto.getBody());
        notificationType.setBody(notificationTypeDto.getSubject());
        return notificationType;
    }

}
