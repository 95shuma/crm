package com.project.crm.backend.services;


import com.project.crm.backend.repository.DoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepo repo;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

}
