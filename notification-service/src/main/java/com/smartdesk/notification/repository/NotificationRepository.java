package com.smartdesk.notification.repository;

import com.smartdesk.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository
        extends JpaRepository<Notification,Long>{

}