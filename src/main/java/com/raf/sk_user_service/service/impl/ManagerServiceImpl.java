package com.raf.sk_user_service.service.impl;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.domain.Manager;
import com.raf.sk_user_service.dto.ManagerCreateDto;
import com.raf.sk_user_service.dto.ManagerDto;
import com.raf.sk_user_service.dto.ManagerUpdatedDto;
import com.raf.sk_user_service.exception.NotFoundException;
import com.raf.sk_user_service.mapper.ManagerMapper;
import com.raf.sk_user_service.repository.ManagerRepository;
import com.raf.sk_user_service.service.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;

    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
    }

    @Override
    public Optional<Manager> logIn(String username, String password) {
        return managerRepository.findManagerByUsernameAndPassword(username, password);
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
}
