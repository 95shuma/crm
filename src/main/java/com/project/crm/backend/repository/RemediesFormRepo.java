package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.remediesCatalog.RemediesForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RemediesFormRepo extends JpaRepository<RemediesForm, Long> {
    Optional<RemediesForm> findByName(String name);
    Optional<RemediesForm> findById(Long id);
}
