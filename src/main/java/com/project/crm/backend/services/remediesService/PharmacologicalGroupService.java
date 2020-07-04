package com.project.crm.backend.services.remediesService;



import com.project.crm.backend.dto.remediesDto.PharmacologicalGroupDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.PharmacologicalGroup;
import com.project.crm.backend.repository.PharmacologicalGroupRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PharmacologicalGroupService {

    private final PharmacologicalGroupRepo pharmacologicalGroupRepo;

    public Page<PharmacologicalGroupDTO> getAll(Pageable pageable){
        return pharmacologicalGroupRepo.findAll(pageable).map(PharmacologicalGroupDTO::from);
    }

    public PharmacologicalGroupDTO createPharmGroup(PharmacologicalGroupDTO pharmGroupDTO){
        var pharmGroup = PharmacologicalGroup.builder()
                .name(pharmGroupDTO.getName())
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
