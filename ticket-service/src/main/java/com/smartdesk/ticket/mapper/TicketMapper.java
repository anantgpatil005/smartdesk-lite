package com.smartdesk.ticket.mapper;

import com.smartdesk.ticket.dto.*;
import com.smartdesk.ticket.entity.Ticket;

public class TicketMapper {

    public static Ticket toEntity(TicketRequest dto){

        return Ticket.builder()

                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())

                .build();

    }

    public static TicketResponse toResponse(Ticket ticket){

        return TicketResponse.builder()

                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())

                .build();

    }

}