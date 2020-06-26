package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.remediesCatalog.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeasureRepo extends JpaRepository<Measure, Long> {
    Optional<Measure> findByName(String name);
    Optional<Measure> findById(Long id);
}
