package com.smartdesk.ticket.service.impl;

import com.smartdesk.ticket.dto.*;
import com.smartdesk.ticket.entity.Ticket;
import com.smartdesk.ticket.mapper.TicketMapper;
import com.smartdesk.ticket.repository.TicketRepository;
import com.smartdesk.ticket.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;

    public TicketServiceImpl(TicketRepository repository) {

        this.repository = repository;

    }

    @Override
    public TicketResponse create(TicketRequest request) {

        Ticket ticket = TicketMapper.toEntity(request);

        ticket = repository.save(ticket);

        return TicketMapper.toResponse(ticket);

    }

    @Override
    public List<TicketResponse> findAll() {

        return repository.findAll()

                .stream()

                .map(TicketMapper::toResponse)

                .toList();

    }

}