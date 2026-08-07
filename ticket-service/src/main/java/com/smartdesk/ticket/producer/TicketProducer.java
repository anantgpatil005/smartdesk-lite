package com.smartdesk.ticket.producer;

import com.smartdesk.ticket.event.TicketCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketProducer {

    private static final String TOPIC = "ticket-created";

    private final KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate;

    public TicketProducer(KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(TicketCreatedEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("Published : " + event);

    }

}