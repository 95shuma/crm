package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.InstrumExaminationDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.InstrumExamination;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.InstrumExaminationRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstrumExaminationService {

    private final InstrumExaminationRepo instrumExaminationRepo;

    public InstrumExaminationDTO getById(Long id){
        InstrumExamination instrumExamination = instrumExaminationRepo.findById(id).get();
        return InstrumExaminationDTO.from(instrumExamination);
    }

    public List<InstrumExaminationDTO> getAll(){
        return instrumExaminationRepo.findAll().stream().map(InstrumExaminationDTO::from).collect(Collectors.toList());
    }

    public Page<InstrumExaminationDTO> getAll(Pageable pageable){
        return instrumExaminationRepo.findAll(pageable).map(InstrumExaminationDTO::from);
    }
}
