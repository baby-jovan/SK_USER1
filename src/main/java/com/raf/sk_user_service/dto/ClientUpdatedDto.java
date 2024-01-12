package com.raf.sk_user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ClientUpdatedDto {
    private String email, username, password, name, lastName;
    private String dateOfBirth;
}
