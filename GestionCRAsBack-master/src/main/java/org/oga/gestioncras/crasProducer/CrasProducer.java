package org.oga.gestioncras.crasProducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.oga.gestioncras.events.CRAsCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CrasProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CRAsCreatedEvent.class);
    private NewTopic topic;
    private KafkaTemplate<String, CRAsCreatedEvent> kafkaTemplate;
    public CrasProducer(NewTopic topic , KafkaTemplate<String,CRAsCreatedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(CRAsCreatedEvent event) {
        LOGGER.info(String.format("set cras => %s", event.toString()));
        // create Message
        Message<CRAsCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "Cras_topic")
                .build();
        kafkaTemplate.send(message);

    }
}
