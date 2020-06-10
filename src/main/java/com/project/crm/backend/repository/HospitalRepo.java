package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HospitalRepo extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByName(String name);
}
