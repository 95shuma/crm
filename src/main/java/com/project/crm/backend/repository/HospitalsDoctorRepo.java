package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.HospitalsDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalsDoctorRepo extends JpaRepository<HospitalsDoctor, Long> {
    List<HospitalsDoctor> getAllByDoctorInn(String inn);
    boolean existsByDoctorInnAndRoleId(String inn, Long id);
}
