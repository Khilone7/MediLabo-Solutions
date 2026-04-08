package com.medilabo.microservicefront.service;

import com.medilabo.microservicefront.configuration.RestTemplateConfig;
import com.medilabo.microservicefront.configuration.UrlConfig;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssessmentService {

    private final RestTemplateConfig restTemplateConfig;
    private final UrlConfig urlConfig;

    public String getAssessmentByPatientId(Long patientId, HttpSession session) {
        String risk = restTemplateConfig.restTemplate(session).getForObject(urlConfig.getAssessment() + "/" + patientId, String.class);
        return risk != null ? risk.replace("\"", "").trim() : "NONE";
    }
}
