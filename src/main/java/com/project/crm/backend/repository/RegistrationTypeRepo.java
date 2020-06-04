package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationTypeRepo extends JpaRepository<RegistrationType, Long> {
}
