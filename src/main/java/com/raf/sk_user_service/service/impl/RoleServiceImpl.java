package com.raf.sk_user_service.service.impl;

import com.raf.sk_user_service.domain.Role;
import com.raf.sk_user_service.repository.RoleRepository;
import com.raf.sk_user_service.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findRole(String name) {
        return roleRepository.findRoleByName(name);
    }
}
