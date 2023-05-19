package com.example.notification.repositories;

import com.example.notification.entities.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notifications, String> {
}
