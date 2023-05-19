package com.oga.leave.queries.kafkaProducer;

import com.oga.leave.events.LeaveApprovedEvent;
import com.oga.leave.events.LeaveCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class ApprooveLeaveProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveApprovedEvent.class);
    private  NewTopic topic;
    private KafkaTemplate<String, LeaveApprovedEvent> kafkaTemplate;
    public ApprooveLeaveProducer(NewTopic topic , KafkaTemplate<String,LeaveApprovedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(LeaveApprovedEvent event) {
        LOGGER.info(String.format(" leave approoved  => %s", event.toString()));
        // create Message
        Message<LeaveApprovedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "leaveApproved_topic")
                .build();
        kafkaTemplate.send(message);

    }
}
