package com.smartdesk.notification.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private Long ticketId;

    private String message;

    private String recipient;

    private Boolean readStatus;

}