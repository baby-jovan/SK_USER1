package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.service.NotificationService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/notification")
@Api(tags = "Notification controller")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<Page<NotificationDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NotificationDto> add(@RequestBody NotificationCreateDto notificationCreateDto){
        return new ResponseEntity<>(notificationService.add(notificationCreateDto), HttpStatus.CREATED);
    }
}
