package com.example.kafka.kafka;

import com.example.kafka.model.Task;
import com.example.kafka.model.User;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


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

    public List<Task> getTasks (Integer id) throws ExecutionException, InterruptedException, TimeoutException {
        LOGGER.info(String.format("Message sent: %s"), id);

        Message<Integer> msg = MessageBuilder
                .withPayload(id)
                .setHeader(KafkaHeaders.TOPIC,"task_json")
                .build();


        return null;

    }


    public void update(String title, String status) {
        LOGGER.info(String.format("Message sent: %s , %s",title,status));


        Message<String> msg = MessageBuilder
                .withPayload(title)
                .setHeader(KafkaHeaders.TOPIC,"javat")
                .build();
        Message<String> msg2 = MessageBuilder
                .withPayload(status)
                .setHeader(KafkaHeaders.TOPIC,"javat")
                .build();

        kafkaTemplate.send(msg);

    }
}
