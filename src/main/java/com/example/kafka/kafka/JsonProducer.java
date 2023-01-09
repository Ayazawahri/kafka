package com.example.kafka.kafka;

import com.example.kafka.model.Task;
import com.example.kafka.model.User;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;


@Service
public class JsonProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(JsonProducer.class);


    private KafkaTemplate<String, Object> kafkaTemplate;


    public JsonProducer(KafkaTemplate<String, Object> kafkaTemplate) {
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

    public List<Object> getTasks (Integer id) throws ExecutionException, InterruptedException, TimeoutException {
        LOGGER.info(String.format("Message sent: %s"), id);

        //RequestReplyFuture<String, Object, List<Object>> future= template.sendAndReceive(new ProducerRecord<>("task_json", 0, null,id));
        //List<Object> tasks = future.get(10, TimeUnit.SECONDS).value();

       /* Message<Integer> msg = MessageBuilder
                .withPayload(id)
                .setHeader(KafkaHeaders.TOPIC,"task_json")
                .build();*/
        //kafkaTemplate.send(msg);

        /*Flux<Task> clientFlux = WebClient.create().get().uri("http://localhost:8080/api/kafka/all/"+id).retrieve()
                .bodyToFlux(Task.class);*/
       // clientFlux.subscribe(task -> LOGGER.info(String.format(task.toString())));


        return null;

    }


    public void update( String m) {
        LOGGER.info(String.format("Message sent: %s ",m));


        Message<String> msg = MessageBuilder
                .withPayload(m)
                .setHeader(KafkaHeaders.TOPIC,"javat")
                .build();




        kafkaTemplate.send(msg);

    }
}
