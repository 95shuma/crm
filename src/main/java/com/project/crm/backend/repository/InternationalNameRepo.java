package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.remediesCatalog.InternationalName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternationalNameRepo extends JpaRepository<InternationalName, Long> {
    Optional<InternationalName> findByName(String name);
    Optional<InternationalName> findById(Long id);
}
