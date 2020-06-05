package com.project.crm.backend.repository;

import com.project.crm.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    boolean existsByInn(String inn);
    Optional<Patient> findByInn(String inn);
}
