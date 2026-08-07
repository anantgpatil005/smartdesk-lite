package com.smartdesk.notification.mapper;

import com.smartdesk.notification.dto.*;
import com.smartdesk.notification.entity.Notification;

public class NotificationMapper {

    public static Notification toEntity(NotificationRequest dto){

        return Notification.builder()

                .ticketId(dto.getTicketId())

                .message(dto.getMessage())

                .recipient(dto.getRecipient())

                .readStatus(false)

                .build();

    }

    public static NotificationResponse toResponse(Notification n){

        return NotificationResponse.builder()

                .id(n.getId())

                .ticketId(n.getTicketId())

                .message(n.getMessage())

                .recipient(n.getRecipient())

                .readStatus(n.getReadStatus())

                .build();

    }

}