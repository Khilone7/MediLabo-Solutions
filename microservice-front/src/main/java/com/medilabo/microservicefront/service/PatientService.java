package com.medilabo.microservicefront.service;

import com.medilabo.microservicefront.configuration.RestTemplateConfig;
import com.medilabo.microservicefront.configuration.UrlConfig;
import com.medilabo.microservicefront.entity.Patient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final RestTemplateConfig restTemplateConfig;
    private final UrlConfig urlConfig;

    public List<Patient> getAllPatients(HttpSession session) {
        Patient[] patients = restTemplateConfig.restTemplate(session).getForObject(urlConfig.getPatients(), Patient[].class);
        return patients == null ? Collections.emptyList() : Arrays.asList(patients);
    }

    public Patient getPatientById(Long id, HttpSession session) {
        return restTemplateConfig.restTemplate(session).getForObject(urlConfig.getPatients() + "/" + id, Patient.class);
    }

    public void createPatient(Patient patient, HttpSession session) {
        restTemplateConfig.restTemplate(session).postForLocation(urlConfig.getPatients(), patient);
    }

    public void updatePatient(Long id, Patient patient, HttpSession session) {
        restTemplateConfig.restTemplate(session).put(urlConfig.getPatients() + "/" + id, patient);
    }

    public void deletePatient(Long id, HttpSession session) {
        restTemplateConfig.restTemplate(session).delete(urlConfig.getPatients() + "/" + id);
    }

}
