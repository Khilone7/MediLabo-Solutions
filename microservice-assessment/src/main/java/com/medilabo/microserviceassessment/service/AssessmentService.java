package com.medilabo.microserviceassessment.service;

import com.medilabo.microserviceassessment.config.RestTemplateConfig;
import com.medilabo.microserviceassessment.config.UrlConfig;
import com.medilabo.microserviceassessment.model.DiabetesIndicators;
import com.medilabo.microserviceassessment.model.DiabetesRisk;
import com.medilabo.microserviceassessment.model.dto.NoteContentDto;
import com.medilabo.microserviceassessment.model.dto.PatientInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final RestTemplateConfig restTemplateConfig;
    private final UrlConfig urlConfig;


    public PatientInfoDto getPatientById(Long id, HttpServletRequest request) {
        return restTemplateConfig.restTemplate(request).getForObject(urlConfig.getPatients() + "/" + id, PatientInfoDto.class);
    }

    public List<String> getsNoteByPatientId(Long id, HttpServletRequest request) {
        NoteContentDto[] notes = restTemplateConfig.restTemplate(request).getForObject(urlConfig.getNotes() + "/" + id, NoteContentDto[].class);
        if (notes == null) {
            return List.of();
        }
        return Arrays.stream(notes).map(NoteContentDto::content).toList();
    }

    public DiabetesRisk assessDiabetesRisk(String gender, LocalDate birthDate, List<String> notesContent) {
        int age = calculateAge(birthDate);
        int indicatorCount = countDistinctIndicators(notesContent);

        if (age < 30) {
            if (gender.equals("M")) {
                return switch (indicatorCount) {
                    case 0, 1, 2 -> DiabetesRisk.NONE;
                    case 3, 4 -> DiabetesRisk.IN_DANGER;
                    default -> DiabetesRisk.EARLY_ONSET;
                };
            } else {
                return switch (indicatorCount) {
                    case 0, 1, 2, 3 -> DiabetesRisk.NONE;
                    case 4, 5, 6 -> DiabetesRisk.IN_DANGER;
                    default -> DiabetesRisk.EARLY_ONSET;
                };
            }
        } else {
            return switch (indicatorCount) {
                case 0, 1 -> DiabetesRisk.NONE;
                case 2, 3, 4, 5 -> DiabetesRisk.BORDERLINE;
                case 6, 7 -> DiabetesRisk.IN_DANGER;
                default -> DiabetesRisk.EARLY_ONSET;
            };
        }
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private int countDistinctIndicators(List<String> notesContent) {
        String allNotes = String.join(" ", notesContent).toLowerCase();

        return (int) DiabetesIndicators.INDICATORS.stream()
                .filter(indicator -> allNotes.contains(indicator.toLowerCase()))
                .count();
    }

}
