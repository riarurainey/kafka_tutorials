package com.example.kafka_tutorials;

import com.example.kafka_tutorials.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class KafkaTutorialsApplication {

    @KafkaListener(topics = "msg")
    public void msgListener(ConsumerRecord<Long, UserDto> record) {
        System.out.println("Record partition: " + record.partition());
        System.out.println("Record key: " + record.key());
        System.out.println("Record value: " + record.value());
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaTutorialsApplication.class, args);
    }

}
