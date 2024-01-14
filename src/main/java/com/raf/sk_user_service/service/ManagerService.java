package com.raf.sk_user_service.service;

import com.raf.sk_user_service.domain.Manager;
import com.raf.sk_user_service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);
    TokenResponseDto logIn(TokenRequestDto tokenRequestDto);
    ManagerDto findById(Long id);
    ManagerDto add(ManagerCreateDto managerCreateDto);

    ManagerDto update(Long id, ManagerUpdatedDto managerUpdatedDto);
    void deleteById(Long id);

    ManagerDto updatePassword(Long id, ManagerUpdatePasswordDto managerUpdatePasswordDto);
}
