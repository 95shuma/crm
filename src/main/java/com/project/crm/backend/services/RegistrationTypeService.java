package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.RegistrationType;
import com.project.crm.backend.repository.RegistrationTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationTypeService {
    @Autowired
    RegistrationTypeRepo registrationTypeRepo;

    public List<RegistrationType> getAll(){
        return registrationTypeRepo.findAll();
    }

    public void save(RegistrationType registrationType){
        registrationTypeRepo.save(registrationType);
    }

    public RegistrationType getByName(String name){
        return registrationTypeRepo.findByName(name);
    }
}
