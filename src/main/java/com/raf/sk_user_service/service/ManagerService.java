package com.raf.sk_user_service.service;

import com.raf.sk_user_service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> findAll();
    TokenResponseDto logIn(TokenRequestDto tokenRequestDto);
    ManagerDto findById(Long id);
    ManagerDto add(ManagerCreateDto managerCreateDto);

    ManagerDto update(Long id, ManagerUpdatedDto managerUpdatedDto);
    void deleteById(Long id);

    ManagerDto updatePassword(Long id, ManagerUpdatePasswordDto managerUpdatePasswordDto);

    ManagerDto setZabrana (zabranaDto zabranaDto);
}
