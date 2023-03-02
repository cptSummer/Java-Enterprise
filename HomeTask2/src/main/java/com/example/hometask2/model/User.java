package com.example.hometask2.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class User {
    @Id
    Long id;
    String firstName;
    String phone;
    String email;

}
