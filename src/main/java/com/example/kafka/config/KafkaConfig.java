package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.config.TopicBuilder;




@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic javaTopic(){
        return TopicBuilder.name("javat")
                .build();
    }

    @Bean
    public NewTopic javajsonTopic(){
        return TopicBuilder.name("javat_json")
                .build();
    }

    @Bean
    public NewTopic taskjsonTopic(){
        return TopicBuilder.name("task_json")
                .build();
    }




}
