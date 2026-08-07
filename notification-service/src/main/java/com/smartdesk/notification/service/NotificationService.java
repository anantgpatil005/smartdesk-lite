package com.smartdesk.notification.service;
import java.util.List;

import com.smartdesk.notification.dto.NotificationRequest;
import com.smartdesk.notification.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse save(NotificationRequest request);

    List<NotificationResponse> findAll();

}