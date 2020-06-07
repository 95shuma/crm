package com.project.crm.backend.services;


import com.project.crm.backend.model.Patient;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepo repo;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

    public void save(Patient patient){
        repo.save(patient);
    }

    public Optional<Patient> getByInn(String inn){
        return repo.findByInn(inn);
    }
}
