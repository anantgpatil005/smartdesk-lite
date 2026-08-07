package com.smartdesk.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    private Long ticketId;

    private String message;

    private String recipient;

}