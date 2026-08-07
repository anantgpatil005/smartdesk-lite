package com.smartdesk.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketId;

    private String message;

    private String recipient;

    private Boolean readStatus;

}