package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.dto.remediesDto.RemedyDTO;
import com.project.crm.backend.model.catalog.Remedy;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.remediesForm.RemedyRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RemedyService {

    private final RemedyRepo remedyRepo;
    private final RemedyTypeRepo remedyTypeRepo;
    private final PharmacologicalGroupRepo pharmacologicalGroupRepo;
    private final InternationalNameRepo internationalNameRepo;
    private final DosageRepo dosageRepo;
    private final RemediesFormRepo remediesFormRepo;


    public List<RemedyDTO> getAll(){
        return remedyRepo.findAll().stream().map(RemedyDTO::from).collect(Collectors.toList());
    }

    public Page<RemedyDTO> getAll(Pageable pageable){
        return remedyRepo.findAll(pageable).map(RemedyDTO::from);
    }



    public void createRemedy(RemedyRegisterForm remedyRegisterForm){
        var remedy = Remedy.builder()
                .remedyType(remedyTypeRepo.findById(remedyRegisterForm.getRemedyTypeId()).get())
                .pharmacologicalGroup(pharmacologicalGroupRepo.findById(remedyRegisterForm.getPharmacologicalGroupId()).get())
                .internationalName(internationalNameRepo.findById(remedyRegisterForm.getInternationalNameId()).get())
                .name(remedyRegisterForm.getName())
                .dosage(dosageRepo.findById(remedyRegisterForm.getDosageId()).get())
                .remediesForm(remediesFormRepo.findById(remedyRegisterForm.getRemediesFormId()).get())
                .build();
        remedyRepo.save(remedy);
    }


    public RemedyDTO getByName(String name){
        Remedy remedy = remedyRepo.findByName(name).get();
        return RemedyDTO.from(remedy);
    }

    public RemedyDTO getById(Long id){
        Remedy remedy = remedyRepo.findById(id).get();
        return RemedyDTO.from(remedy);
    }
}
