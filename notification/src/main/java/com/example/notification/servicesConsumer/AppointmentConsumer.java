package com.example.notification.servicesConsumer;

import com.example.Erp.commonApi.event.TacheEvent.TacheTestedEvent;
import com.example.notification.entities.Notifications;
import com.example.notification.repositories.NotificationRepository;
import com.oga.rendezvous.events.AppointmentCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentConsumer.class);
    @KafkaListener(topics = "appointment_topic",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AppointmentCreatedEvent event){
        LOGGER.info(event.getCollaboraterId().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("new appointment is in set ");
        notifications.setCollaborator(event.getCollaboraterId().toString());
        notifications.setResponsible(event.getHumanResourcesManagerId().toString());
        notificationRepository.save(notifications);

    }
}
