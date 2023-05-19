package com.example.notification.servicesConsumer;

import com.example.Erp.commonApi.event.TacheEvent.TacheTestedEvent;
import com.example.notification.repositories.NotificationRepository;
import com.example.notification.entities.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaskConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConsumer.class);
    @KafkaListener(topics = "tache_topic",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(TacheTestedEvent event){
        LOGGER.info(event.getCollaborator().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("new tache is in test ");
        notifications.setCollaborator(event.getCollaborator().toString());
        notifications.setResponsible(event.getResponsible().toString());
        notificationRepository.save(notifications);

    }
}
