package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepo extends JpaRepository<Place, Long> {

    Optional<Place> findByName(String name);
    boolean existsByCodePlace(Long codePlace);
}
