package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.model.catalog.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepo extends JpaRepository<Role, Long> {
     Role findByName(String n);
}
