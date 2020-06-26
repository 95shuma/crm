package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DosageRepo extends JpaRepository<Dosage, Long> {
    Optional<Dosage> findByName(String name);
    Optional<Dosage> findById(Long id);
}
