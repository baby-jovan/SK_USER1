package com.raf.sk_user_service.service;

import com.raf.sk_user_service.domain.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRole(String name);
}
