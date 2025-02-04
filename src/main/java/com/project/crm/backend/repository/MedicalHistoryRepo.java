package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.RecordJournal;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory, Long> {
    Optional<MedicalHistory> findById(Long id);
}
