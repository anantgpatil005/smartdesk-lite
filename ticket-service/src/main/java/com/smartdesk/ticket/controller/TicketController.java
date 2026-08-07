package com.smartdesk.ticket.controller;

import com.smartdesk.ticket.dto.*;
import com.smartdesk.ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins="http://localhost:5173")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service){

        this.service=service;

    }

    @PostMapping
    public TicketResponse create(

            @Valid

            @RequestBody

            TicketRequest request){

        return service.create(request);

    }

    @GetMapping
    public List<TicketResponse> getAll(){

        return service.findAll();

    }

}