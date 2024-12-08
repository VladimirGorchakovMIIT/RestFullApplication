package com.example.vladimir.services.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Setter
@Service
@RequiredArgsConstructor
public class MessageSender {
    @Value("${queue.name}")
    private String queueName;

    private final AmqpTemplate amqpTemplate;

    public void send(String message){
        amqpTemplate.convertAndSend(queueName, message);
    }
}
