package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.repository.HospitalRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {

    private final HospitalRepo hospitalRepo;

    public List<Hospital> getAll(){
        return hospitalRepo.findAll();
    }

    public Hospital getByName(String name){
        return hospitalRepo.findByName(name).get();
    }

    public void createHospital(String name, Place place, String address){
        var hospital = Hospital.builder()
                .name(name)
                .place(place)
                .address(address)
                .build();
        hospitalRepo.save(hospital);
    }
}
