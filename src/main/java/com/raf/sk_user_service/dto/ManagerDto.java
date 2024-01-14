package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.Gym;
import com.raf.sk_user_service.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ManagerDto {
    private Long id,isZabrana;
    private String username, email, name, lastName;
    private String dateOfBirth, dateOfEmpl;
    private Role role;
    private List<Gym> gyms;
}
