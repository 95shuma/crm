package com.project.crm.backend.services;

import com.project.crm.backend.dto.HospitalDTO;
import com.project.crm.backend.dto.PlaceDTO;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.repository.HospitalRepo;
import com.project.crm.backend.repository.PlaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {

    private final PlaceRepo placeRepo;
    private final HospitalRepo hospitalRepo;

    public List<Hospital> getAll(){
        return hospitalRepo.findAll();
    }

    public HospitalDTO getById(Long id){
        Hospital hospital = hospitalRepo.findById(id).get();
        return HospitalDTO.from(hospital);
    }

    public void createHospital(String name, PlaceDTO placeDTO, String address){
        var hospital = Hospital.builder()
                .name(name)
                .place(placeRepo.findById(placeDTO.getId()).get())
                .address(address)
                .build();
        hospitalRepo.save(hospital);
    }
}
