package com.medilabo.microservicefront.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Note {

    private String id;
    private Long patientId;
    private String content;
    private LocalDate date;
}
