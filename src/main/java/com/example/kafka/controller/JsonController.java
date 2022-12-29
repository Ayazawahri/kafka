package com.example.kafka.controller;

import com.example.kafka.kafka.JsonConsumer;
import com.example.kafka.kafka.JsonProducer;
import com.example.kafka.model.Task;
import com.example.kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@CrossOrigin
@RestController
@RequestMapping("/api/kafka")
public class JsonController {



    private JsonProducer jsonProducer;
    private JsonConsumer jsonConsumer;


    @Autowired
    public JsonController(JsonProducer jsonProducer, JsonConsumer jsonConsumer) {
        this.jsonProducer = jsonProducer;
        this.jsonConsumer = jsonConsumer;
    }

   /* public JsonController(JsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

   @Autowired
    public JsonController(JsonConsumer jsonConsumer) {
        this.jsonConsumer = jsonConsumer;
    }*/




    @PostMapping(value = { "/publish" })
    public ResponseEntity<String> publish(@RequestBody User user){
        jsonProducer.sendMessage(user);
        return ResponseEntity.ok("  Json message is sent to the topic");


    }

    @PostMapping("/publishTask")
    public ResponseEntity<String> publish(@RequestBody Task task){
        jsonProducer.sendTask(task);
        return ResponseEntity.ok("  Json message is sent to the topic");


    }

    @GetMapping("/all/{id}")
    public ResponseEntity <List<Task>> getAllTasksByUser(@PathVariable("id") Integer id) throws ExecutionException, InterruptedException, TimeoutException {
        List<Task> tasks = jsonConsumer.getTask(id);

        return new  ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity <String> updateTask(@PathVariable ("id") Integer id, @PathVariable ("status") String status) throws Exception {
         jsonConsumer.updateTask(id, status);
        return ResponseEntity.ok("  Json message is sent to the topic");
    }





}
