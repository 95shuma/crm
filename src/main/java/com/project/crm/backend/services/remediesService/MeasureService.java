package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.dto.remediesDto.InternationalNameDTO;
import com.project.crm.backend.dto.remediesDto.MeasureDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.Measure;
import com.project.crm.backend.repository.MeasureRepo;
import com.project.crm.frontend.forms.remediesForm.MeasureRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MeasureService {

    private final MeasureRepo measureRepo;

    public Page<MeasureDTO> getAll(Pageable pageable){
        return measureRepo.findAll(pageable).map(MeasureDTO::from);
    }

    public List<MeasureDTO> getAll(){
        return measureRepo.findAll().stream().map(MeasureDTO::from).collect(Collectors.toList());
    }

    public void  createMeasure(MeasureRegisterForm measureRegisterForm){
        var measure = Measure.builder()
                .name(measureRegisterForm.getName())
                .build();
        measureRepo.save(measure);
    }

    public MeasureDTO getByName(String name){
        Measure measure = measureRepo.findByName(name).get();
        return MeasureDTO.from(measure);
    }

    public MeasureDTO getById(Long id){
        Measure measure = measureRepo.findById(id).get();
        return MeasureDTO.from(measure);
    }
}
