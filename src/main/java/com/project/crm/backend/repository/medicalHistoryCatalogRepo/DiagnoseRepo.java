package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseRepo extends JpaRepository<Diagnose, Long> {
}
