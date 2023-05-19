package com.example.notification.servicesConsumer;

import com.example.Erp.commonApi.event.TacheEvent.TacheTestedEvent;
import com.example.notification.entities.Notifications;
import com.example.notification.repositories.NotificationRepository;
import com.oga.leave.events.LeaveCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class leaveConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(leaveConsumer.class);
    @KafkaListener(topics = "leave_topic",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(LeaveCreatedEvent event){
        LOGGER.info(event.getCollaboraterId().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("new ask for leave  is coming ");
        notifications.setCollaborator(event.getCollaboraterId().toString());
        notifications.setResponsible(event.getHumanResourcesManagerId().toString());
        notificationRepository.save(notifications);

    }
}
