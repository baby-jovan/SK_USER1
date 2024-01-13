package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String email, username, name, lastName;

    private String dateOfBirth;
    private Role role;
}
