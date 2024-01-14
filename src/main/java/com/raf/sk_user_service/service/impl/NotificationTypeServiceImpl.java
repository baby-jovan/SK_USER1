package com.raf.sk_user_service.service.impl;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.domain.NotificationType;
import com.raf.sk_user_service.dto.NotificationTypeDto;
import com.raf.sk_user_service.exception.NotFoundException;
import com.raf.sk_user_service.mapper.NotificationTypeMapper;
import com.raf.sk_user_service.repository.NotificationTypeRepository;
import com.raf.sk_user_service.service.NotificationTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public List<NotificationTypeDto> findAll() {
        return notificationTypeRepository.findAll().stream()
                .map(notificationTypeMapper::notificationTypeToNotificationTypeDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationTypeDto findById(Long id) {
        return null;
    }

    @Override
    public NotificationTypeDto add(NotificationTypeDto notificationTypeDto) {
        NotificationType notificationType = new NotificationType();
        notificationType.setNotificationType(notificationTypeDto.getNotificationType());
        notificationType.setBody(notificationTypeDto.getBody());
        notificationType.setSubject(notificationTypeDto.getSubject());


        notificationTypeRepository.save(notificationType);
        return notificationTypeDto;
    }

    @Override
    public NotificationTypeDto update(Long id, NotificationTypeDto notificationTypeDto) {
        NotificationType notificationType = notificationTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("NotificationType with id: " + id + " not found.")
        );
        notificationType.setNotificationType(notificationTypeDto.getNotificationType());
        notificationType.setBody(notificationTypeDto.getBody());
        notificationType.setSubject(notificationTypeDto.getSubject());

        notificationTypeRepository.save(notificationType);

        return notificationTypeDto;
    }

    @Override
    public void deleteById(Long id) {
        notificationTypeRepository.deleteById(id);
    }
}
