package com.raf.sk_user_service.service.impl;

import com.raf.sk_user_service.domain.Manager;
import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.exception.NotFoundException;
import com.raf.sk_user_service.mapper.ManagerMapper;
import com.raf.sk_user_service.repository.ManagerRepository;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;
    private TokenService tokenService;

    public ManagerServiceImpl(ManagerRepository managerRepository, TokenService tokenService, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.tokenService = tokenService;
    }

    @Override
    public List<ManagerDto> findAll() {
        return managerRepository.findAll().stream()
                .map(managerMapper::managerToManagerDto)
                .collect(Collectors.toList());
    }

    @Override
    public TokenResponseDto logIn(TokenRequestDto tokenRequestDto) throws NullPointerException{
        Manager manager = managerRepository.findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword());

        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        claims.put("role", manager.getRole().getName());
        claims.put("email", manager.getEmail());
        claims.put("zabrana", manager.getIsZabrana());

        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ManagerDto findById(Long id) {
        return managerRepository.findById(id).map(managerMapper::managerToManagerDto).orElseThrow(() ->
                new NotFoundException("Manager with id: " + id + " not found.")
        );
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto update(Long id, ManagerUpdatedDto managerUpdatedDto) {
        Manager manager = managerRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Manager with id: " + id + " not found.")
        );
        manager.setEmail(managerUpdatedDto.getEmail());
        manager.setName(managerUpdatedDto.getName());
        manager.setLastName(managerUpdatedDto.getLastName());
        manager.setDateOfBirth(managerUpdatedDto.getDateOfBirth());
        manager.setUsername(managerUpdatedDto.getUsername());
        manager.setDateOfBirth(managerUpdatedDto.getDateOfBirth());
       // manager.setGyms(managerUpdatedDto.getGyms());

        return managerMapper.managerToManagerDto(managerRepository.save(manager));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public ManagerDto updatePassword(Long id, ManagerUpdatePasswordDto managerUpdatePasswordDto) {
        Manager manager = managerRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Manager with id: " + id + " not found.")
        );

        manager.setPassword(managerUpdatePasswordDto.getPassword());
        return managerMapper.managerToManagerDto(managerRepository.save(manager));
    }

    @Override
    public ManagerDto setZabrana(zabranaDto zabranaDto) {
        Manager manager = managerRepository.findById(zabranaDto.getUserID()).orElseThrow(() ->
                new NotFoundException("Manager with id: " + zabranaDto.getUserID() + " not found.")
        );

        manager.setIsZabrana(zabranaDto.getIsZabrana());

        return managerMapper.managerToManagerDto(managerRepository.save(manager));

    }
}
