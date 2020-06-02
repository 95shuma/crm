package com.project.crm.backend.repository;

import com.project.crm.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByInn(String inn);
    boolean existsByInn(String inn);
}
