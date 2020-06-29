package com.project.crm.backend.services;


import com.project.crm.backend.dto.remediesDto.RemedyTypeDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.RemedyType;
import com.project.crm.backend.repository.RemedyTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RemedyTypeService {

    private final RemedyTypeRepo remedyTypeRepo;

    public Page<RemedyTypeDTO> getAll(Pageable pageable){
        return remedyTypeRepo.findAll(pageable).map(RemedyTypeDTO::from);
    }

    public RemedyTypeDTO createRemedyType(RemedyTypeDTO typeDTO){
        var type = RemedyType.builder()
                .name(typeDTO.getName())
                .build();
        return RemedyTypeDTO.from(remedyTypeRepo.save(type));
    }

    public RemedyTypeDTO getByName(String name){
        RemedyType type = remedyTypeRepo.findByName(name).get();
        return RemedyTypeDTO.from(type);
    }

}
