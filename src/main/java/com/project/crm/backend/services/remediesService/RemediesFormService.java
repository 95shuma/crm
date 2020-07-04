package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.dto.remediesDto.RemediesFormDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.RemediesForm;
import com.project.crm.backend.repository.RemediesFormRepo;
import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RemediesFormService {

    private final RemediesFormRepo remediesFormRepo;

    public Page<RemediesFormDTO> getAll(Pageable pageable){
        return remediesFormRepo.findAll(pageable).map(RemediesFormDTO::from);
    }
    public List<RemediesFormDTO> getAll(){
        return remediesFormRepo.findAll().stream().map(RemediesFormDTO::from).collect(Collectors.toList());
    }
    public RemediesFormDTO createRemediesForm(RemediesFormRegisterForm remediesFormRegisterForm){
        var form = RemediesForm.builder()
                .name(remediesFormRegisterForm.getName())
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
