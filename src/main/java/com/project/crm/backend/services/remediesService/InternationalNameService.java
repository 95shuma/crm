package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.dto.remediesDto.InternationalNameDTO;
import com.project.crm.backend.dto.remediesDto.PharmacologicalGroupDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import com.project.crm.backend.model.catalog.remediesCatalog.InternationalName;
import com.project.crm.backend.repository.InternationalNameRepo;
import com.project.crm.frontend.forms.remediesForm.DosageRegisterForm;
import com.project.crm.frontend.forms.remediesForm.InternationalNameRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class InternationalNameService {

    private final InternationalNameRepo internationalNameRepo;

    public Page<InternationalNameDTO> getAll(Pageable pageable){
        return internationalNameRepo.findAll(pageable).map(InternationalNameDTO::from);
    }

    public List<InternationalNameDTO> getAll(){
        return internationalNameRepo.findAll().stream().map(InternationalNameDTO::from).collect(Collectors.toList());
    }


    public void createInternationalName(InternationalNameRegisterForm internationalNameRegisterForm){
        var internationalName = InternationalName.builder()
                .name(internationalNameRegisterForm.getName())
                .build();
        internationalNameRepo.save(internationalName);
    }
    public InternationalNameDTO getByName(String name){
        InternationalName internationalName = internationalNameRepo.findByName(name).get();
        return InternationalNameDTO.from(internationalName);
    }

    public InternationalNameDTO getById(Long id){
        InternationalName internationalName = internationalNameRepo.findById(id).get();
        return InternationalNameDTO.from(internationalName);
    }
}
