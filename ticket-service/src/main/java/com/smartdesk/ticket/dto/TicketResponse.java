package com.smartdesk.ticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {

    private Long id;

    private String title;

    private String description;

    private String status;

    private String priority;

}