package com.medilabo.microserviceassessment.controller;

import com.medilabo.microserviceassessment.model.DiabetesRisk;
import com.medilabo.microserviceassessment.model.dto.PatientInfoDto;
import com.medilabo.microserviceassessment.service.AssessmentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/assessment/")
public class AssessmentController {

    private final AssessmentService assessmentService;

    @GetMapping("{patientId}")
    public DiabetesRisk getAssessmentByPatientId(@PathVariable Long patientId, HttpServletRequest request) {
        PatientInfoDto patientInfo = assessmentService.getPatientById(patientId, request);
        List<String> notes = assessmentService.getsNoteByPatientId(patientId, request);
        return assessmentService.assessDiabetesRisk(patientInfo.gender(), patientInfo.birthDate(), notes);

    }
}
