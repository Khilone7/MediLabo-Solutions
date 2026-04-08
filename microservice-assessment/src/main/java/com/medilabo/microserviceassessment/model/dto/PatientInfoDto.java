package com.medilabo.microserviceassessment.model.dto;

import java.time.LocalDate;

public record PatientInfoDto(LocalDate birthDate, String gender) {
}
