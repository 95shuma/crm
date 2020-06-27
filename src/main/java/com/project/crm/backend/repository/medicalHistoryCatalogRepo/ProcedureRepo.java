package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepo extends JpaRepository<Procedure, Long> {
}
