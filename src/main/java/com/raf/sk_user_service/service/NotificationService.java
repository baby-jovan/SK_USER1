package com.raf.sk_user_service.service;

import com.raf.sk_user_service.dto.ClientDto;
import com.raf.sk_user_service.dto.ClientUpdatedDto;
import com.raf.sk_user_service.dto.NotificationCreateDto;
import com.raf.sk_user_service.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> findAll();
    List<NotificationDto> findFor(Long id);
    NotificationDto add(NotificationCreateDto notificationCreateDto);
    ClientDto update(Long id, ClientUpdatedDto clientUpdatedDto);
    void deleteById(Long id);

}
