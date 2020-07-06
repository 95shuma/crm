package com.project.crm.backend.repository.medicalHistoryCatalogRepo;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface TreatmentRepo extends JpaRepository<Treatment, Long> {
    @Query(value = "select * from treatments as t order by t.id asc", nativeQuery = true)
    Page<Treatment> findAll(Pageable pageable);
}
