package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.Gym;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ManagerDto {
    private Long id;
    private String username, email, name, lastName;
    private String dateOfBirth, dateOfEmpl;
    private List<Gym> gyms;
}
