package com.project.crm.backend.services;


import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.HospitalsDoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HospitalsDoctorService {

    private final HospitalsDoctorRepo repo;

    /*public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }*/



}
