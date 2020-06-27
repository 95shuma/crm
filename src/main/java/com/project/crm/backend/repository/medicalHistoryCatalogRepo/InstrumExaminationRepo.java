package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.InstrumExamination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumExaminationRepo extends JpaRepository<InstrumExamination, Long> {
}
