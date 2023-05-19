package com.oga.leave.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Value("leave_topic")
    private String topicName;
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
                .replicas(1)
                .partitions(10)
                .build();
    }


}
