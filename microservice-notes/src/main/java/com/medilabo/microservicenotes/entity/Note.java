package com.medilabo.microservicenotes.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "notes")
@Data
public class Note {

    @Id
    private String id;
    private Long patientId;
    private String content;
    private LocalDate date;
}
