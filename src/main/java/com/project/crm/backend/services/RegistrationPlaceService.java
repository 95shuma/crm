package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.repository.RegistrationPlaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationPlaceService {

    @Autowired
    RegistrationPlaceRepo registrationPlaceRepo;

    public List<RegistrationPlace> getAll(){
        return registrationPlaceRepo.findAll();
    }

    public void save(String name, String code_place, Integer groupCode){
        RegistrationPlace registrationPlace = RegistrationPlace.builder()
                .name(name)
                .code_place(code_place)
                .group_code(groupCode)
                .build();
        registrationPlaceRepo.save(registrationPlace);
    }

    public RegistrationPlace getByName(String name){
        return registrationPlaceRepo.findByName(name);
    }

    public RegistrationPlace getById(Long id){
        return registrationPlaceRepo.findRegistrationPlaceById(id);
    }
}
