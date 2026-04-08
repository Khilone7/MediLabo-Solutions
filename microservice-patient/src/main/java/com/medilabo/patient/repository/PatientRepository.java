package com.medilabo.patient.repository;

import com.medilabo.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
