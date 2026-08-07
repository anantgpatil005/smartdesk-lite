package com.smartdesk.ticket.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartdesk.ticket.dto.TicketRequest;
import com.smartdesk.ticket.dto.TicketResponse;
import com.smartdesk.ticket.entity.Ticket;
import com.smartdesk.ticket.event.TicketCreatedEvent;
import com.smartdesk.ticket.mapper.TicketMapper;
import com.smartdesk.ticket.producer.TicketProducer;
import com.smartdesk.ticket.repository.TicketRepository;
import com.smartdesk.ticket.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;
    private final TicketProducer producer;

    public TicketServiceImpl(
            TicketRepository repository,
            TicketProducer producer) {

        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public TicketResponse create(TicketRequest request) {

        Ticket ticket = TicketMapper.toEntity(request);

        Ticket saved = repository.save(ticket);

        producer.publish(

                TicketCreatedEvent.builder()

                        .ticketId(saved.getId())

                        .title(saved.getTitle())

                        .description(saved.getDescription())

                        .status(saved.getStatus())

                        .priority(saved.getPriority())

                        .build()

        );

        return TicketMapper.toResponse(saved);

    }

    @Override
    public List<TicketResponse> findAll() {

        return repository.findAll()

                .stream()

                .map(TicketMapper::toResponse)

                .toList();

    }

}