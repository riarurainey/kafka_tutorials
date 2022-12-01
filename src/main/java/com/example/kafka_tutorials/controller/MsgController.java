package com.example.kafka_tutorials.controller;

import com.example.kafka_tutorials.dto.Address;
import com.example.kafka_tutorials.dto.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("msg")
public class MsgController {

    private final KafkaTemplate<Long, UserDto> kafkaTemplate;

    public MsgController(KafkaTemplate<Long, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendOrder(Long msgId, UserDto msg) {
        msg.setAddress(new Address("Rus", "Msk", "Tverskaya", 13L, 4L));
        CompletableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, msg);

        future.whenComplete((input, ex) -> {
            if (ex != null) {
                System.out.println(ex.getMessage());
            } else {
                System.out.println("No exception, got result" + input);
            }
        });
        kafkaTemplate.flush();
    }

}
