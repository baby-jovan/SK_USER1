package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.security.CheckSecurity;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.NotificationService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/notification")
@Api(tags = "Notification controller")
public class NotificationController {
    private NotificationService notificationService;
    private TokenService tokenService;

    public NotificationController(NotificationService notificationService, TokenService tokenService) {
        this.notificationService = notificationService;
        this.tokenService = tokenService;
    }
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<NotificationDto>> findAll(@RequestHeader("Authorization") String authorization){
        List<NotificationDto> notificationList = notificationService.findAll();
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER"})
    @GetMapping("/get_notify")
    public ResponseEntity<List<NotificationDto>> findFor(@RequestHeader("Authorization") String authorization){
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        Long userId = Long.parseLong(claims.get("id").toString());
        List<NotificationDto> notificationList = notificationService.findFor(userId);
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NotificationDto> add(@RequestBody NotificationCreateDto notificationCreateDto){
        return new ResponseEntity<>(notificationService.add(notificationCreateDto), HttpStatus.CREATED);
    }
}
