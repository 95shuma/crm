package com.project.crm.backend.services;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.SickListDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.SickList;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.SickListRepo;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class SickListService {

    private final SickListRepo sickListRepo;



    public SickListDTO getById(Long id){
        SickList sickList = sickListRepo.findById(id).get();
        return SickListDTO.from(sickList);
    }


    public List<SickList> getAll(){return sickListRepo.findAll();}





}