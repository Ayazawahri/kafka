package com.example.kafka.controller;

import com.example.kafka.kafka.JsonProducer;
import com.example.kafka.model.Task;
import com.example.kafka.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/kafka")
public class JsonController {

    private JsonProducer jsonProducer;


    public JsonController(JsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

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




}
