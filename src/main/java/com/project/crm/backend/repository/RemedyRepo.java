package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Remedy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RemedyRepo extends JpaRepository<Remedy, Long> {

    Optional<Remedy> findByName(String name);
    Optional<Remedy> findById(Long id);

}
