package com.example.Erp.commonApi.ProducerKafka;

import com.example.Erp.commonApi.event.ProjectEvent.ProjectCreatedEvent;
import com.example.Erp.commonApi.event.TacheEvent.TacheTestedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TaskProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TacheTestedEvent.class);
    private NewTopic topic;
    private KafkaTemplate<String, TacheTestedEvent> kafkaTemplate;
    public TaskProducer(NewTopic topic, KafkaTemplate<String,TacheTestedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(TacheTestedEvent event) {
        LOGGER.info(String.format("Tache event et a tester  => %s", event.toString()));
        // create Message
        Message<TacheTestedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "tache_topic")
                .build();
        kafkaTemplate.send(message);

    }
}
