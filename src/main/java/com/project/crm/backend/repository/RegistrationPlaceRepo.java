package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RegistrationPlaceRepo extends JpaRepository<RegistrationPlace, Long> {
    RegistrationPlace findByName(String name);
    RegistrationPlace findRegistrationPlaceById(Long id);
    List<RegistrationPlace> findAll();
}
