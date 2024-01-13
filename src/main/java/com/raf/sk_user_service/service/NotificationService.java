package com.raf.sk_user_service.service;

import com.raf.sk_user_service.dto.ClientDto;
import com.raf.sk_user_service.dto.ClientUpdatedDto;
import com.raf.sk_user_service.dto.NotificationCreateDto;
import com.raf.sk_user_service.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    Page<NotificationDto> findAll(Pageable pageable);
    NotificationDto add(NotificationCreateDto notificationCreateDto);
    ClientDto update(Long id, ClientUpdatedDto clientUpdatedDto);
    void deleteById(Long id);

}
