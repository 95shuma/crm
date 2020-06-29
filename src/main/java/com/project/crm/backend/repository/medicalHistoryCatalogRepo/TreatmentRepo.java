package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepo extends JpaRepository<Treatment, Long> {
}
