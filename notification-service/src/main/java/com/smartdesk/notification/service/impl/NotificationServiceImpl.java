package com.smartdesk.notification.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartdesk.notification.dto.NotificationRequest;
import com.smartdesk.notification.dto.NotificationResponse;
import com.smartdesk.notification.entity.Notification;
import com.smartdesk.notification.mapper.NotificationMapper;
import com.smartdesk.notification.repository.NotificationRepository;
import com.smartdesk.notification.service.NotificationService;

@Service
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository){

        this.repository=repository;

    }

    @Override
    public NotificationResponse save(NotificationRequest request){

        Notification notification =
                NotificationMapper.toEntity(request);

        notification = repository.save(notification);

        return NotificationMapper.toResponse(notification);

    }

    @Override
    public List<NotificationResponse> findAll(){

        return repository.findAll()

                .stream()

                .map(NotificationMapper::toResponse)

                .toList();

    }

}
