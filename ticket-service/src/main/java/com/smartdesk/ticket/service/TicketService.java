package com.smartdesk.ticket.service;

import com.smartdesk.ticket.entity.Ticket;
import com.smartdesk.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

}