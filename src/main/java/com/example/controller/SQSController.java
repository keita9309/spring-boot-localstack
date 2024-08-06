package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.model.Message;
import com.example.service.SQSService;

@RestController
public class SQSController {

    private final SQSService sqsService;

    @Autowired
    public SQSController(SQSService sqsService) {
        this.sqsService = sqsService;
    }

    @GetMapping("/create-queue")
    public String createQueue(@RequestParam String queueName) {
        return sqsService.createQueue(queueName);
    }

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam String queueUrl, @RequestParam String message) {
        sqsService.sendMessage(queueUrl, message);
        return "Message sent: " + message;
    }

    @GetMapping("/receive-messages")
    public List<Message> receiveMessages(@RequestParam String queueUrl) {
        return sqsService.receiveMessages(queueUrl);
    }
}