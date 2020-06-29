package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.SickList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SickListRepo extends JpaRepository<SickList, Long> {
}
