package com.example.notification.servicesConsumer;

import com.example.Erp.commonApi.event.TacheEvent.TacheTestedEvent;
import com.example.notification.entities.Notifications;
import com.example.notification.repositories.NotificationRepository;
import org.oga.gestioncras.events.CRAsCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CrasConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CrasConsumer.class);
    @KafkaListener(topics = "Cras_topic",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CRAsCreatedEvent event){
        LOGGER.info(event.getIdCollaborator().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("new Cras  is in created ");
        notifications.setCollaborator(event.getIdCollaborator().toString());
        notifications.setResponsible(event.getIdResponsible().toString());
        notificationRepository.save(notifications);

    }
}
