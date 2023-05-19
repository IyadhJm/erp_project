package com.example.notification.servicesConsumer;

import com.example.Erp.commonApi.event.ProjectEvent.ProjectCreatedEvent;
import com.example.notification.repositories.NotificationRepository;
import com.example.notification.entities.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProjectConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ProjectCreatedEvent event){
        LOGGER.info(event.getCollaborators().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("new Project Created For user  ");
        notifications.setCollaborator(event.getCollaborators().toString());
        notificationRepository.save(notifications);

    }
}
