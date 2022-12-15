package com.dhashuk.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonBirthdayDTO {
    String firstName;
    String secondName;
    int age;
}
