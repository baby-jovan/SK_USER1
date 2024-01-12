package com.raf.sk_user_service.mapper;

import com.raf.sk_user_service.domain.Manager;
import com.raf.sk_user_service.dto.ManagerCreateDto;
import com.raf.sk_user_service.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    public ManagerDto managerToManagerDto(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setName(manager.getName());
        managerDto.setEmail(manager.getEmail());
        managerDto.setUsername(manager.getUsername());
        managerDto.setLastName(manager.getLastName());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        //managerDto.setGyms(manager.getGyms());
        managerDto.setDateOfEmpl(manager.getDateOfEmpl());
        return managerDto;
    }

    public Manager managerDtoToManager(ManagerCreateDto managerCreateDto){
        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setRole(managerCreateDto.getRole());
        return manager;
    }
}
