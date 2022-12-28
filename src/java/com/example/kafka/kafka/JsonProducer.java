package com.example.kafka.kafka;

import com.example.kafka.model.Task;
import com.example.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class JsonProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(JsonProducer.class);

    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){
        LOGGER.info(String.format("Message sent: %s", user.toString()));

        Message<User> msg = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC,"javat_json")
                .build();

        kafkaTemplate.send(msg);

    }

    public void sendTask(Task task){
        LOGGER.info(String.format("Message sent: %s",task.toString()));


        Message<Task> msg = MessageBuilder
                .withPayload(task)
                .setHeader(KafkaHeaders.TOPIC,"task_json")
                .build();



        kafkaTemplate.send(msg);



    }




}
