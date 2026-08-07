package com.smartdesk.notification.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCreatedEvent {

    private Long ticketId;

    private String title;

    private String description;

    private String status;

    private String priority;

}
