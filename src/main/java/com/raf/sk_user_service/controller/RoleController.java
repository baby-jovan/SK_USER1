package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.domain.Role;
import com.raf.sk_user_service.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Optional<Role>> findByName(@ApiIgnore String name){
        return new ResponseEntity<>(roleService.findRole(name), HttpStatus.OK);
    }
}
