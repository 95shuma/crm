package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DosageRepo extends JpaRepository<Dosage, Long> {
    Optional<Dosage> findByName(String name);
    Optional<Dosage> findById(Long id);
    @Query(value = "select * from dosages as d order by d.name asc", nativeQuery = true)
    Page<Dosage> findAll(Pageable page);
}
