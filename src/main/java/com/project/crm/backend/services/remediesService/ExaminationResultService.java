package com.project.crm.backend.services.remediesService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.ExaminationResultDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.ExaminationResult;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.ExaminationResultRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.InstrumExaminationRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.LabExaminationRepo;
import com.project.crm.frontend.forms.remediesForm.ExaminationResultForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExaminationResultService {

    private final ExaminationResultRepo examinationResultRepo;
    private final InstrumExaminationRepo instrumExaminationRepo;
    private final LabExaminationRepo labExaminationRepo;
    private final MedicalHistoryRepo medicalHistoryRepo;

    public List<ExaminationResultDTO> getAll(){
        return examinationResultRepo.findAll().stream().map(ExaminationResultDTO::from).collect(Collectors.toList());
    }

    public Page<ExaminationResultDTO> getAll(Pageable pageable, Long medicalHistoryId){
        return examinationResultRepo.findAllByMedicalHistoryId(medicalHistoryId, pageable).map(ExaminationResultDTO::from);
    }

    public void createExaminationResult(ExaminationResultForm examinationResultForm){


        ExaminationResult examinationResult = ExaminationResult.builder()
                .instrumExamination(instrumExaminationRepo.findById(examinationResultForm.getInstrumExaminationId()).get())
                .instrumExaminationResult(examinationResultForm.getInstrumExaminationResult())
                .labExamination(labExaminationRepo.findById(examinationResultForm.getLabExaminationId()).get())
                .labExaminationResult(examinationResultForm.getLabExaminationResult())
                .generalState(examinationResultForm.getGeneralState())
                .medicalHistory(medicalHistoryRepo.findById(examinationResultForm.getMedicalHistoryId()).get())
                .build();

        examinationResultRepo.save(examinationResult);
    }
}
