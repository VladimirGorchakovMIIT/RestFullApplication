package com.example.vladimir.controllers.rabbitmq;

import com.example.vladimir.services.rabbitmq.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rabbit")
@RequiredArgsConstructor
public class RabbitMQController {
    private final MessageSender messageSender;

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody String message){
        if (message.isBlank()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        messageSender.send(message);

        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
