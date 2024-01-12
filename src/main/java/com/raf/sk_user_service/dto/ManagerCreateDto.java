package com.raf.sk_user_service.dto;

import com.raf.sk_user_service.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerCreateDto {
    private String email, username;
    private String password;
    private Role role;
}
