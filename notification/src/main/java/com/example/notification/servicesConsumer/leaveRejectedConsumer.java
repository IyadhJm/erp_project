package com.example.notification.servicesConsumer;

import com.example.notification.entities.Notifications;
import com.example.notification.repositories.NotificationRepository;
import com.oga.leave.events.LeaveApprovedEvent;
import com.oga.leave.events.LeaveRejectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class leaveRejectedConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(leaveRejectedConsumer.class);
    @KafkaListener(topics = "leaverejected_topic",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(LeaveRejectedEvent event){
        LOGGER.info(event.getCollaboraterId().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("Leave rejected  ");
        notifications.setCollaborator(event.getCollaboraterId().toString());
        notifications.setResponsible(event.getHumanResourcesManagerId().toString());
        notificationRepository.save(notifications);

    }
}
