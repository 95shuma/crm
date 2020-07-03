package com.project.crm.backend.services;


import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.dto.remediesDto.InternationalNameDTO;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.remediesCatalog.InternationalName;
import com.project.crm.backend.repository.InternationalNameRepo;
import com.project.crm.frontend.forms.InternationalNameRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class InternationalNameService {

    private final InternationalNameRepo internationalNameRepo;

    public Page<InternationalNameDTO> getAll(Pageable pageable){
        return internationalNameRepo.findAll(pageable).map(InternationalNameDTO::from);
    }

    public List<InternationalNameDTO> getAll(){
        List<InternationalName> names = new ArrayList<InternationalName>();
        names = internationalNameRepo.findAll();

        List<InternationalNameDTO> internationalNameDTO = new ArrayList<InternationalNameDTO>();
        names.stream().forEach(obj -> {
            internationalNameDTO.add(InternationalNameDTO.from(obj));
        });
        return internationalNameDTO;
    }

    public InternationalNameDTO createInternationalName(@Valid InternationalNameRegisterForm internationalNameDTO){
        var internationalName = InternationalName.builder()
                .name(internationalNameDTO.getName())
                .build();
        return InternationalNameDTO.from(internationalNameRepo.save(internationalName));
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
