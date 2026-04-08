package com.medilabo.patient.controller;

import com.medilabo.patient.entity.Patient;
import com.medilabo.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,@Valid @RequestBody Patient updatedPatient) {
        return ResponseEntity.ok(patientService.updatePatient(id, updatedPatient));
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
     public ResponseEntity<Void> createPatient(@Valid @RequestBody Patient patient) {
        Patient created = patientService.createPatient(patient);
        URI location = URI.create("/api/patients/" + created.getId());
        return ResponseEntity.created(location).build();
    }

}
