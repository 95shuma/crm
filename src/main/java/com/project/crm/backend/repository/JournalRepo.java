package com.project.crm.backend.repository;

import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalRepo extends JpaRepository<Journal, Long> {
    List<Journal> findByDoctorId(Long id);
    List<Journal> findByPatientId(Long id);
}