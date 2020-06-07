package com.project.crm.backend.repository;

import com.project.crm.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByInn(String inn);
    Doctor findByName(String name);
    boolean existsByInn(String inn);
}
