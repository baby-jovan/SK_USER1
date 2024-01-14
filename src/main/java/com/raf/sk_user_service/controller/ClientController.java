package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.domain.Notification;
import com.raf.sk_user_service.domain.NotificationType;
import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.listener.helper.MessageHelper;
import com.raf.sk_user_service.repository.ClientRepository;
import com.raf.sk_user_service.repository.NotificationRepository;
import com.raf.sk_user_service.repository.NotificationTypeRepository;
import com.raf.sk_user_service.security.CheckSecurity;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.ClientService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")//ovde definisemo putanju na koju se zahtev salje
@Api(tags = "Client Controller")
public class ClientController {
    private ClientService clientService;
    private ClientRepository clientRepository;
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationRepository notificationRepository;
    private TokenService tokenService;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String orderDestination;

    @Value("${oauth.jwt.secret}")
    private String secretKey;

    ///ovde ide jos svasta nesto


    public ClientController(ClientService clientService, TokenService tokenService, JmsTemplate jmsTemplate,
                            MessageHelper messageHelper, ClientRepository clientRepository, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository, @Value("${destination.notify}") String orderDestination){
        this.clientService = clientService;
        this.tokenService = tokenService;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.orderDestination = orderDestination;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
        this.clientRepository = clientRepository;
    }

    @ApiOperation(value = "Get all clients")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number do you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of itams to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, value = "BLABLABLA NESTO ZA SORTING IDK", dataType = "string", paramType = "query")
    })

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll(@RequestHeader("Authorization") String authorization){
        List<ClientDto> clientList = clientService.findAll();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
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
        NotificationType notificationType = notificationTypeRepository.findNotificationTypeByNotificationType("REGISTER_TYPE");
        jmsTemplate.convertAndSend(orderDestination,
                messageHelper.createTextMessage(new NotificationDto(clientCreateDto.getEmail(), notificationType)));
        Notification notification = new Notification();
        ResponseEntity<ClientDto> responseEntity = new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
        Client tmp = clientRepository.findClientByUsernameAndPassword(clientCreateDto.getUsername(), clientCreateDto.getPassword());
        notification.setUserId(tmp.getId());
        notification.setNotificationType(notificationType);
        notification.setEmailTo(clientCreateDto.getEmail());
        notificationRepository.save(notification);
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> update(@RequestHeader(name = "Authorization") String token, @RequestBody ClientUpdatedDto clientUpdatedDto) {
        Claims claims = tokenService.parseToken(token.split(" ")[1]);
        Long userId = Long.parseLong(claims.get("id").toString());
        return new ResponseEntity<>(clientService.update(userId, clientUpdatedDto), HttpStatus.OK);
    }

    @PutMapping("/change_password")
    public ResponseEntity<ClientDto> updatePassword(@RequestHeader(name = "Authorization") String token, @RequestBody ClientUpdatePasswordDto clientUpdatePasswordDto) {
        Claims claims = tokenService.parseToken(token.split(" ")[1]);
        if (claims != null) {
            Long userId = Long.parseLong(claims.get("id").toString());
            String email = claims.get("email").toString();
            Notification notification = new Notification();
            NotificationType notificationType = notificationTypeRepository.findNotificationTypeByNotificationType("PASSWORD_TYPE");
            jmsTemplate.convertAndSend(orderDestination,
                    messageHelper.createTextMessage(new NotificationDto(email, notificationType)));
            notification.setUserId(userId);
            notification.setNotificationType(notificationType);
            notification.setEmailTo(email);
            notificationRepository.save(notification);
            return new ResponseEntity<>(clientService.updatePassword(userId, clientUpdatePasswordDto), HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("/update-zabrana")
    @CheckSecurity(roles = "ROLE_ADMIN")
    public ResponseEntity<ClientDto> updateZabrana(@RequestHeader("Authorization") String authorization , @RequestBody zabranaDto zabranaDto) {
        return new ResponseEntity<>(clientService.setZabrana(zabranaDto), HttpStatus.OK);
    }

}
