package com.oga.leave.queries.kafkaProducer;

import com.oga.leave.events.LeaveRejectedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class RejectedLeaveProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveRejectedEvent.class);
    private  NewTopic topic;
    private KafkaTemplate<String, LeaveRejectedEvent> kafkaTemplate;
    public RejectedLeaveProducer(NewTopic topic , KafkaTemplate<String,LeaveRejectedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(LeaveRejectedEvent event) {
        LOGGER.info(String.format(" leave approoved  => %s", event.toString()));
        // create Message
        Message<LeaveRejectedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "leaverejected_topic")
                .build();
        kafkaTemplate.send(message);

    }
}
