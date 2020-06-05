package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.model.catalog.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalsDoctorRepo extends JpaRepository<HospitalsDoctor, Long> {
}
