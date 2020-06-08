package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.repository.HospitalRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {

    HospitalRepo hospitalRepo;

    public List<Hospital> getAll(){
        return hospitalRepo.findAll();
    }

    public Hospital getByName(String name){
        return hospitalRepo.findByName(name);
    }

    public void save(String name, RegistrationPlace registrationPlace, String address){
        Hospital hospital = Hospital.builder()
                .name(name)
                .registrationPlace(registrationPlace)
                .address(address)
                .build();
        hospitalRepo.save(hospital);
    }
}
