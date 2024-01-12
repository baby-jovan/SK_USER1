package com.raf.sk_user_service.service;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {
    Page<ClientDto> findAll(Pageable pageable);
    TokenResponseDto logIn(TokenRequestDto tokenRequestDto);
    ClientDto findById(Long id);
    ClientDto add(ClientCreateDto clientCreateDto);

    ClientDto update(Long id, ClientUpdatedDto clientUpdatedDto);
    void deleteById(Long id);
}
