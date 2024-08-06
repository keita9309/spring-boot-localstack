package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Service
public class SQSService {

    private final AmazonSQS amazonSQS;

    @Autowired
    public SQSService(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public String createQueue(String queueName) {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return amazonSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    public void sendMessage(String queueUrl, String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, message);
        amazonSQS.sendMessage(sendMessageRequest);
    }
    
    public List<Message> receiveMessages(String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        System.out.println("取得したメッセージ：" + amazonSQS.receiveMessage(receiveMessageRequest).getMessages());
        return amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
    }

}
