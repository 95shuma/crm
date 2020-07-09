package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseResultRepo extends JpaRepository<DiagnoseResult, Long> {
    Page<DiagnoseResult> findAllByMedicalHistoryId(Long id, Pageable pageable);
}
