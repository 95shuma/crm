package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.DirectionDTO;
import com.project.crm.backend.dto.medicalHistoryCatalogDTO.ExaminationResultDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Direction;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import com.project.crm.frontend.forms.medicalHistoryForms.DirectionRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DirectionService {

    private final DirectionRepo directionRepo;
    private final LabExaminationRepo labExaminationRepo;
    private final InstrumExaminationRepo instrumExaminationRepo;
    private final PositionRepo positionRepo;
    private final MedicalHistoryRepo medicalHistoryRepo;
    private final RecordJournalRepo recordJournalRepo;

    public List<DirectionDTO> getAll(){
        return directionRepo.findAll().stream().map(DirectionDTO::from).collect(Collectors.toList());
    }

    public Page<DirectionDTO> getAll(Pageable pageable, Long medicalHistoryId){
        return directionRepo.findAllByMedicalHistoryId(medicalHistoryId, pageable).map(DirectionDTO::from);
    }

    public Page<DirectionDTO> getAllByRecord(Pageable pageable, Long record_id){ ;
        return directionRepo.findAllByMedicalHistoryId(recordJournalRepo.findById(record_id).get().getMedicalHistory().getId(), pageable).map(DirectionDTO::from);
    }

    public Page<DirectionDTO> getAll(Pageable pageable){
        return directionRepo.findAll(pageable).map(DirectionDTO::from);
    }

    public void createDirection(DirectionRegisterForm directionRegisterForm){
        var direction = Direction.builder()
                .instrumExamination(instrumExaminationRepo.findById(directionRegisterForm.getInstrumExaminationId()).get())
                .labExamination(labExaminationRepo.findById(directionRegisterForm.getLabExaminationId()).get())
                .position(positionRepo.findById(directionRegisterForm.getPositionId()).get())
                .medicalHistory(medicalHistoryRepo.findById(directionRegisterForm.getMedicalHistoryId()).get())
                .build();
        directionRepo.save(direction);
    }
}
