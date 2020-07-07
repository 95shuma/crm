package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.ExaminationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationResultRepo extends JpaRepository<ExaminationResult, Long> {

    Page<ExaminationResult> findAllByMedicalHistoryId(Long id, Pageable pageable);
}
