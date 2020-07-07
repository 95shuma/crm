package com.project.crm.backend.services;


import com.project.crm.backend.dto.medicalHistoryCatalogDTO.SickListDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.SickList;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.SickListRepo;
import com.project.crm.frontend.forms.SickListRegisterForm;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class SickListService {

    private final SickListRepo sickListRepo;
    private final MedicalHistoryRepo medicalHistoryRepo;

    public SickListDTO getById(Long id){
        SickList sickList = sickListRepo.findById(id).get();
        return SickListDTO.from(sickList);
    }

    public List<SickList> getAll(){return sickListRepo.findAll();}

    public Page<SickListDTO> getAllSickLists(Pageable pageable, Long medicalHistoryId){
        return sickListRepo.findAllByMedicalHistoryId(pageable,medicalHistoryId).map(SickListDTO::from);
    }

    public void createSickList(SickListRegisterForm sickListRegisterForm){
        var user = SickList.builder()
                .number(Long.valueOf(sickListRegisterForm.getNumber()))
                .startDate(sickListRegisterForm.getStartDate())
                .endDate(sickListRegisterForm.getEndDate())
                .medicalHistory(medicalHistoryRepo.findById(sickListRegisterForm.getMedicalHistoryId()).get())
                .build();
        sickListRepo.save(user);
    }

    public SickListDTO getByName(Long number){
        SickList sickList = sickListRepo.findByNumber(number).get();
        return SickListDTO.from(sickList);
    }

}