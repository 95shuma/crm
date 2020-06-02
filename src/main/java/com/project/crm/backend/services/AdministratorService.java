package com.project.crm.backend.services;


import com.project.crm.backend.repository.AdministratorRepo;
import com.project.crm.backend.repository.DoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdministratorService {

    private final AdministratorRepo repo;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

}
