package com.dam.publisher.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
public class SampleController {

    private static final String EXCHANGE_NAME = "sample.exchange";
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SampleController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/queue")
    public String samplePublish(@RequestBody String msg) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.dam0", msg);
        return "message sending!";
    }
}