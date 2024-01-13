package com.raf.sk_user_service.service.impl;

import com.raf.sk_user_service.domain.Notification;
import com.raf.sk_user_service.dto.ClientDto;
import com.raf.sk_user_service.dto.ClientUpdatedDto;
import com.raf.sk_user_service.dto.NotificationCreateDto;
import com.raf.sk_user_service.dto.NotificationDto;
import com.raf.sk_user_service.mapper.NotificationMapper;
import com.raf.sk_user_service.repository.NotificationRepository;
import com.raf.sk_user_service.service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable).map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public NotificationDto add(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationDtoToNotification(notificationCreateDto);
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public ClientDto update(Long id, ClientUpdatedDto clientUpdatedDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
