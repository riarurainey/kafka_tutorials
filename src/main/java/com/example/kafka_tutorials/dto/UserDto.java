package com.example.kafka_tutorials.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long age;
    private String name;
    private Address address;

}
