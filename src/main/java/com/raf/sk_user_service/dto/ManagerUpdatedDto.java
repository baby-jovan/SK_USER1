package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.Gym;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ManagerUpdatedDto {
    private String email, username, name, lastName;
    private String dateOfBirth;
    private List<Gym> gyms;
}
