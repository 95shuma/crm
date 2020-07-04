package com.project.crm.backend.services.remediesService;



import com.project.crm.backend.dto.remediesDto.PharmacologicalGroupDTO;
import com.project.crm.backend.dto.remediesDto.RemedyTypeDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.PharmacologicalGroup;
import com.project.crm.backend.repository.PharmacologicalGroupRepo;
import com.project.crm.frontend.forms.remediesForm.PharmacologicalGroupRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PharmacologicalGroupService {

    private final PharmacologicalGroupRepo pharmacologicalGroupRepo;

    public Page<PharmacologicalGroupDTO> getAll(Pageable pageable){
        return pharmacologicalGroupRepo.findAll(pageable).map(PharmacologicalGroupDTO::from);
    }
    public List<PharmacologicalGroupDTO> getAll(){
        return pharmacologicalGroupRepo.findAll().stream().map(PharmacologicalGroupDTO::from).collect(Collectors.toList());
    }

    public PharmacologicalGroupDTO createPharmGroup(PharmacologicalGroupRegisterForm pharmacologicalGroupRegisterForm){
        var pharmGroup = PharmacologicalGroup.builder()
                .name(pharmacologicalGroupRegisterForm.getName())
                .build();
        return PharmacologicalGroupDTO.from(pharmacologicalGroupRepo.save(pharmGroup));
    }

    public PharmacologicalGroupDTO getByName(String name){
        PharmacologicalGroup pharmGroup = pharmacologicalGroupRepo.findByName(name).get();
        return PharmacologicalGroupDTO.from(pharmGroup);
    }
    public PharmacologicalGroupDTO getById(Long id){
        PharmacologicalGroup pharmGroup = pharmacologicalGroupRepo.findById(id).get();
        return PharmacologicalGroupDTO.from(pharmGroup);
    }
}
