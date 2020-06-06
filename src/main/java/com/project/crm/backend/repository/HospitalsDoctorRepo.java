package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.HospitalsDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalsDoctorRepo extends JpaRepository<HospitalsDoctor, Long> {

}
