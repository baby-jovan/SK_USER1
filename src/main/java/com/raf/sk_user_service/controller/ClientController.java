package com.raf.sk_user_service.controller;

import java.util.Base64;
import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@RequestMapping("/client")//ovde definisemo putanju na koju se zahtev salje
@Api(tags = "Client Controller")
public class ClientController {
    private ClientService clientService;
    private TokenService tokenService;

    @Value("${oauth.jwt.secret}")
    private String secretKey;

    ///ovde ide jos svasta nesto


    public ClientController(ClientService clientService, TokenService tokenService){
        this.clientService = clientService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "Get all clients")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number do you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of itams to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, value = "BLABLABLA NESTO ZA SORTING IDK", dataType = "string", paramType = "query")
    })

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> logIn(@RequestBody TokenRequestDto tokenRequestDto){
        return new ResponseEntity<>(clientService.logIn(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDto> add(@RequestBody ClientCreateDto clientCreateDto){
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> update(@RequestHeader(name = "Authorization") String token, @RequestBody ClientUpdatedDto clientUpdatedDto) {
        System.out.println(token);
        Claims claims = tokenService.parseToken(token);
        Long userId = Long.parseLong(claims.getSubject());
        System.out.println("AAAAAAAAA" + userId);
        return new ResponseEntity<>(clientService.update(userId, clientUpdatedDto), HttpStatus.OK);
    }

}
