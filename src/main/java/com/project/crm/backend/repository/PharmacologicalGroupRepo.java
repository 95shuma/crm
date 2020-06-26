package com.project.crm.backend.repository;



import com.project.crm.backend.model.catalog.remediesCatalog.PharmacologicalGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmacologicalGroupRepo extends JpaRepository<PharmacologicalGroup, Long> {

    Optional<PharmacologicalGroup> findByName(String name);
    Optional<PharmacologicalGroup> findById(Long id);
}
