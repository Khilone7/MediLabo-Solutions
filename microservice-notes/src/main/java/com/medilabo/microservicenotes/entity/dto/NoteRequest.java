package com.medilabo.microservicenotes.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteRequest(@NotBlank String content) {
}
