package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.DiagnoseDTO;
import com.project.crm.backend.dto.medicalHistoryCatalogDTO.DiagnoseResultDTO;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.DiagnoseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagnoseService {

    private final DiagnoseRepo diagnoseRepo;

    public List<DiagnoseDTO> getAll(){
        return diagnoseRepo.findAll().stream().map(DiagnoseDTO::from).collect(Collectors.toList());
    }
}
