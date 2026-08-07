package com.smartdesk.ticket.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    @GetMapping("/hello")
    public String hello() {
        return "Ticket Service Running";
    }
}