package com.smartdesk.ticket.service;

import com.smartdesk.ticket.dto.*;

import java.util.List;

public interface TicketService {

    TicketResponse create(TicketRequest request);

    List<TicketResponse> findAll();

}