package com.smartdesk.notification.consumer;

import com.smartdesk.notification.entity.Notification;
import com.smartdesk.notification.event.TicketCreatedEvent;
import com.smartdesk.notification.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationRepository repository;

    public NotificationConsumer(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(
            topics = "ticket-created",
            groupId = "notification-group")
    public void consume(TicketCreatedEvent event) {

        Notification notification = Notification.builder()

                .ticketId(event.getTicketId())

                .message("Ticket Created : " + event.getTitle())

                .recipient("ADMIN")

                .readStatus(false)

                .build();

        repository.save(notification);

        System.out.println("Notification Saved");

    }

}