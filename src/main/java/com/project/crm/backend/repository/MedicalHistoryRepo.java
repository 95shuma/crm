package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory, Long> {
}
