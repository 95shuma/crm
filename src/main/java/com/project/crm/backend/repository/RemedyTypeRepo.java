package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.remediesCatalog.RemedyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RemedyTypeRepo extends JpaRepository<RemedyType, Long> {
    Optional<RemedyType> findByName(String name);
}
