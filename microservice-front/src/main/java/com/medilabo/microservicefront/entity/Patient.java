package com.medilabo.microservicefront.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
}
