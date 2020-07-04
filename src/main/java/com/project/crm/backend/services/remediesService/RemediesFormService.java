package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.dto.remediesDto.RemediesFormDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.RemediesForm;
import com.project.crm.backend.repository.RemediesFormRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RemediesFormService {

    private final RemediesFormRepo remediesFormRepo;

    public Page<RemediesFormDTO> getAll(Pageable pageable){
        return remediesFormRepo.findAll(pageable).map(RemediesFormDTO::from);
    }

    public RemediesFormDTO createRemediesForm(RemediesFormDTO formDTO){
        var form = RemediesForm.builder()
                .name(formDTO.getName())
                .build();
        return RemediesFormDTO.from(remediesFormRepo.save(form));
    }

    public RemediesFormDTO getByName(String name){
        RemediesForm form = remediesFormRepo.findByName(name).get();
        return RemediesFormDTO.from(form);
    }
    public RemediesFormDTO getById(Long id){
        RemediesForm form = remediesFormRepo.findById(id).get();
        return RemediesFormDTO.from(form);
    }
}
