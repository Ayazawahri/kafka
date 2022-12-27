package com.example.kafka.controller;


import com.example.kafka.kafka.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class Controller {

    private Producer producer;

    public Controller(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish( @RequestParam("msg") String msg){
        producer.sendMessage(msg);
        return ResponseEntity.ok("  Json message is sent to the topic");


    }
}
