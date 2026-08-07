package com.smartdesk.notification.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartdesk.notification.dto.NotificationRequest;
import com.smartdesk.notification.dto.NotificationResponse;
import com.smartdesk.notification.service.NotificationService;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins="http://localhost:5173")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service){

        this.service=service;

    }

    @PostMapping
    public NotificationResponse create(

            @RequestBody NotificationRequest request){

        return service.save(request);

    }

    @GetMapping
    public List<NotificationResponse> getAll(){

        return service.findAll();

    }

}
