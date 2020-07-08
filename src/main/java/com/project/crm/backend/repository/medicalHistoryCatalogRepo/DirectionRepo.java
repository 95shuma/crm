package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.DirectionDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Direction;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.ExaminationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectionRepo extends JpaRepository<Direction, Long> {
    Page<Direction> findAllByMedicalHistoryId(Long id, Pageable pageable);
}
