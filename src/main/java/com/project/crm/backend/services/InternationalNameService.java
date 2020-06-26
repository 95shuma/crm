package com.project.crm.backend.services;


import com.project.crm.backend.dto.remediesDto.InternationalNameDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.InternationalName;
import com.project.crm.backend.repository.InternationalNameRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class InternationalNameService {

    private final InternationalNameRepo internationalNameRepo;

    public Page<InternationalNameDTO> getAll(Pageable pageable){
        return internationalNameRepo.findAll(pageable).map(InternationalNameDTO::from);
    }

    public InternationalNameDTO createInternationalName(InternationalNameDTO internationalNameDTO){
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
