package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseResultRepo extends JpaRepository<DiagnoseResult, Long> {
}
