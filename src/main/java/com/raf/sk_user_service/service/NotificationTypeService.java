package com.raf.sk_user_service.service;

import com.raf.sk_user_service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationTypeService {
    List<NotificationTypeDto> findAll();
    NotificationTypeDto findById(Long id);
    NotificationTypeDto add(NotificationTypeDto notificationTypeDto);

    NotificationTypeDto update(Long id, NotificationTypeDto notificationTypeDto);
    void deleteById(Long id);
}
