package com.oga.leave.queries.kafkaProducer;

import com.oga.leave.events.LeaveCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;


@Service
public class LeaveProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveCreatedEvent.class);
    private  NewTopic topic;
    private KafkaTemplate<String, LeaveCreatedEvent> kafkaTemplate;
    public LeaveProducer(NewTopic topic , KafkaTemplate<String,LeaveCreatedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(LeaveCreatedEvent event) {
        LOGGER.info(String.format("ask for leave  => %s", event.toString()));
        // create Message
        Message<LeaveCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "leave_topic")
                .build();
        kafkaTemplate.send(message);

    }
}
