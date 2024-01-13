package com.raf.sk_user_service.controller;

import com.raf.sk_user_service.dto.NotificationTypeDto;
import com.raf.sk_user_service.service.NotificationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification-type")
public class NotificationTypeController {

    private final NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationTypeDto>> findAll() {
        List<NotificationTypeDto> notificationTypeList = notificationTypeService.findAll();
        return new ResponseEntity<>(notificationTypeList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<NotificationTypeDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(notificationTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NotificationTypeDto> add(@RequestBody NotificationTypeDto notificationTypeDto) {
        return new ResponseEntity<>(notificationTypeService.add(notificationTypeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationTypeDto> update(@PathVariable Long id, @RequestBody NotificationTypeDto notificationTypeDto) {
        return new ResponseEntity<>(notificationTypeService.update(id, notificationTypeDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        notificationTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
