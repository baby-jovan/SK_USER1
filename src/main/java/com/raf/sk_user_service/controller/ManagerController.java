package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.domain.Manager;
import com.raf.sk_user_service.dto.ClientCreateDto;
import com.raf.sk_user_service.dto.ClientDto;
import com.raf.sk_user_service.dto.ManagerCreateDto;
import com.raf.sk_user_service.dto.ManagerDto;
import com.raf.sk_user_service.service.ManagerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping
    public ResponseEntity<Page<ManagerDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Optional<Manager>> logIn(@ApiIgnore String username, @ApiIgnore String password){
        return new ResponseEntity<>(managerService.logIn(username, password), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> add(@RequestBody ManagerCreateDto managerCreateDto){
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        managerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
