package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.domain.Manager;
import com.raf.sk_user_service.domain.Notification;
import com.raf.sk_user_service.domain.NotificationType;
import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.listener.helper.MessageHelper;
import com.raf.sk_user_service.repository.ManagerRepository;
import com.raf.sk_user_service.repository.NotificationRepository;
import com.raf.sk_user_service.repository.NotificationTypeRepository;
import com.raf.sk_user_service.security.CheckSecurity;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;
    private ManagerRepository managerRepository;
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationRepository notificationRepository;
    private JmsTemplate jmsTemplate;
    private TokenService tokenService;
    private MessageHelper messageHelper;
    private String orderDestination;
    @Value("${oauth.jwt.secret}")
    private String secretKey;

    public ManagerController(ManagerService managerService, ManagerRepository managerRepository,
                             @Value("${destination.notify}") String orderDestination, MessageHelper messageHelper,
                             JmsTemplate jmsTemplate, TokenService tokenService, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.managerService = managerService;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
        this.jmsTemplate = jmsTemplate;
        this.orderDestination = orderDestination;
        this.managerRepository = managerRepository;
        this.messageHelper = messageHelper;
        this.tokenService = tokenService;
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<ManagerDto>> findAll(@RequestHeader("Authorization") String authorization){
        List<ManagerDto> managerList = managerService.findAll();
        return new ResponseEntity<>(managerList, HttpStatus.OK);
    }
    @ApiOperation(value = "Login")
    @GetMapping("/login")
    public ResponseEntity<TokenResponseDto> logIn(@RequestBody TokenRequestDto tokenRequestDto){
        return new ResponseEntity<>(managerService.logIn(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> add(@RequestBody ManagerCreateDto managerCreateDto){
        NotificationType notificationType = notificationTypeRepository.findNotificationTypeByNotificationType("REGISTER_TYPE");
        jmsTemplate.convertAndSend(orderDestination,
                messageHelper.createTextMessage(new NotificationDto(managerCreateDto.getEmail(), notificationType)));
        Notification notification = new Notification();
        ResponseEntity<ManagerDto> responseEntity = new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
        Manager tmp = managerRepository.findManagerByUsernameAndPassword(managerCreateDto.getUsername(), managerCreateDto.getPassword());
        notification.setUserId(tmp.getId());
        notification.setNotificationType(notificationType);
        notification.setEmailTo(managerCreateDto.getEmail());
        notificationRepository.save(notification);
        return responseEntity;

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        managerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change_password")
    public ResponseEntity<ManagerDto> updatePassword(@RequestHeader(name = "Authorization") String token, @RequestBody ManagerUpdatePasswordDto managerUpdatePasswordDto) {
        Claims claims = tokenService.parseToken(token.split(" ")[1]);
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
        return new ResponseEntity<>(managerService.updatePassword(userId, managerUpdatePasswordDto), HttpStatus.OK);
    }

    @PutMapping("/update-zabrana")
    @CheckSecurity(roles = "ROLE_ADMIN")
    public ResponseEntity<ManagerDto> updateZabrana(@RequestHeader("Authorization") String authorization , @RequestBody zabranaDto zabranaDto) {
        return new ResponseEntity<>(managerService.setZabrana(zabranaDto), HttpStatus.OK);
    }

}
