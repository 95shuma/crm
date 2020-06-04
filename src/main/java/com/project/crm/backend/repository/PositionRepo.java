package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PositionRepo extends JpaRepository<Position, Long> {
}
