package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface HospitalRepo extends JpaRepository<Hospital, Long> {
    Hospital findByName(String name);
}
