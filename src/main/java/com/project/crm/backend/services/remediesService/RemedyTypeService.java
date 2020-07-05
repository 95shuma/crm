package com.project.crm.backend.services.remediesService;



import com.project.crm.backend.dto.remediesDto.RemedyTypeDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.RemedyType;
import com.project.crm.backend.repository.RemedyTypeRepo;
import com.project.crm.frontend.forms.remediesForm.RemedyTypeRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RemedyTypeService {

    private final RemedyTypeRepo remedyTypeRepo;

    public Page<RemedyTypeDTO> getAll(Pageable pageable){
        return remedyTypeRepo.findAll(pageable).map(RemedyTypeDTO::from);
    }

    public List<RemedyTypeDTO> getAll(){
        return remedyTypeRepo.findAll().stream().map(RemedyTypeDTO::from).collect(Collectors.toList());
    }


    public void createRemedyType(RemedyTypeRegisterForm remedyTypeRegisterForm){
        var type = RemedyType.builder()
                .name(remedyTypeRegisterForm.getName())
                .build();
       remedyTypeRepo.save(type);
    }

    public RemedyTypeDTO getByName(String name){
        RemedyType type = remedyTypeRepo.findByName(name).get();
        return RemedyTypeDTO.from(type);
    }

}
