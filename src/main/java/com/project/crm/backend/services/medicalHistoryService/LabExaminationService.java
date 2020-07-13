package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.LabExaminationDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.LabExamination;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.LabExaminationRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LabExaminationService {

    private final LabExaminationRepo labExaminationRepo;

    public LabExaminationDTO getById(Long id){
        LabExamination labExamination = labExaminationRepo.findById(id).get();
        return LabExaminationDTO.from(labExamination);
    }

    public List<LabExaminationDTO> getAll(){
        return labExaminationRepo.findAll().stream().map(LabExaminationDTO::from).collect(Collectors.toList());
    }

    public Page<LabExaminationDTO> getAll(Pageable pageable){
        return labExaminationRepo.findAll(pageable).map(LabExaminationDTO::from);
    }
}
