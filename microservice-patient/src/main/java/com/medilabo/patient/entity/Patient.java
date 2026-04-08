package com.medilabo.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Champ obligatoire")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Champ obligatoire")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Champ obligatoire")
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Champ obligatoire")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String address;

    @Column
    @Pattern(regexp = "^$|^\\d{3}-\\d{3}-\\d{4}$", message = "Format attendu : 000-000-0000")
    private String phoneNumber;
}
