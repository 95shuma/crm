package com.project.crm.backend.repository;

import com.project.crm.backend.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepo extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByInn(String inn);
    boolean existsByInn(String inn);
}
