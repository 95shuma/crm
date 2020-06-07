package com.project.crm.backend.services;


import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.repository.DoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepo repo;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

    public void save(Doctor doctor){
        repo.save(doctor);
    }

    public Doctor getByInn(String inn){
        return repo.findByInn(inn);
    }

    public Doctor getByName(String name){
        return repo.findByName(name);
    }
}
