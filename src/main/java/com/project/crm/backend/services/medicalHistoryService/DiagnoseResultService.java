package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.DiagnoseResultDTO;
import com.project.crm.backend.dto.medicalHistoryCatalogDTO.DirectionDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Direction;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import com.project.crm.frontend.forms.medicalHistoryForms.DiagnoseResultRegisterForm;
import com.project.crm.frontend.forms.medicalHistoryForms.DirectionRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagnoseResultService {

    private final DiagnoseResultRepo diagnoseResultRepo;
    private final DiagnoseRepo diagnoseRepo;
    private final MedicalHistoryRepo medicalHistoryRepo;

    public List<DiagnoseResultDTO> getAll(){
        return diagnoseResultRepo.findAll().stream().map(DiagnoseResultDTO::from).collect(Collectors.toList());
    }

    public Page<DiagnoseResultDTO> getAll(Pageable pageable, Long medicalHistoryId){
        return diagnoseResultRepo.findAllByMedicalHistoryId(medicalHistoryId, pageable).map(DiagnoseResultDTO::from);
    }

    public Page<DiagnoseResultDTO> getAll(Pageable pageable){
        return diagnoseResultRepo.findAll(pageable).map(DiagnoseResultDTO::from);
    }

    public void createDiagnoseResult(DiagnoseResultRegisterForm diagnoseResultRegisterForm){
        var diagnoseResult = DiagnoseResult.builder()
                .diagnose(diagnoseRepo.findById(diagnoseResultRegisterForm.getDiagnoseId()).get())
                .medicalHistory(medicalHistoryRepo.findById(diagnoseResultRegisterForm.getMedicalHistoryId()).get())
                .state(Boolean.parseBoolean(diagnoseResultRegisterForm.getState()))
                .build();
        diagnoseResultRepo.save(diagnoseResult);
    }
}
