package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.dto.TokenRequestDto;
import com.raf.sk_user_service.dto.TokenResponseDto;
import com.raf.sk_user_service.repository.ClientRepository;
import com.raf.sk_user_service.repository.ManagerRepository;
import com.raf.sk_user_service.security.CheckSecurity;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.ClientService;
import com.raf.sk_user_service.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginControler {

    private ManagerService managerService;
    private ManagerRepository managerRepository;
    private ClientService clientService;
    private ClientRepository clientRepository;
    private TokenService tokenService;

    public LoginControler(ManagerService managerService, ManagerRepository managerRepository, ClientService clientService, ClientRepository clientRepository, TokenService tokenService) {
        this.managerService = managerService;
        this.managerRepository = managerRepository;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "Login")
    @PostMapping()
    public ResponseEntity<TokenResponseDto> logIn(@RequestBody TokenRequestDto tokenRequestDto){
        ResponseEntity<TokenResponseDto> login = null;
        try{
             login  =new ResponseEntity<>(clientService.logIn(tokenRequestDto), HttpStatus.OK);
        } catch (Exception e){

        }
        try{
             login = new ResponseEntity<>(managerService.logIn(tokenRequestDto), HttpStatus.OK);
        } catch (Exception e){

        }
        System.out.println(tokenService.parseToken( login.getBody().getToken()).get("zabrana"));
        if(tokenService.parseToken( login.getBody().getToken()).get("zabrana").equals(0)){
            login = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return login;
    }
}
