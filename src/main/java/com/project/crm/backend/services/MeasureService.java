package com.project.crm.backend.services;


import com.project.crm.backend.dto.remediesDto.MeasureDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.Measure;
import com.project.crm.backend.repository.MeasureRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MeasureService {

    private final MeasureRepo measureRepo;

    public Page<MeasureDTO> getAll(Pageable pageable){
        return measureRepo.findAll(pageable).map(MeasureDTO::from);
    }

    public MeasureDTO createMeasure(MeasureDTO measureDTO){
        var measure = Measure.builder()
                .name(measureDTO.getName())
                .build();
        return MeasureDTO.from(measureRepo.save(measure));
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
