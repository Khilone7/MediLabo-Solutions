package com.medilabo.patient.service;

import com.medilabo.patient.entity.Patient;
import com.medilabo.patient.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient introuvable avec l'id : " + id));
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient introuvable avec l'id : " + id));

        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setBirthDate(updatedPatient.getBirthDate());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient introuvable avec l'id : " + id);
        }
        patientRepository.deleteById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
