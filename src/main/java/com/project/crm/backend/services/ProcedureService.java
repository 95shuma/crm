package com.project.crm.backend.services;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.ProcedureDTO;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.ProcedureRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcedureService {
    private final ProcedureRepo procedureRepo;

    public List<ProcedureDTO> getAll(){
        return procedureRepo.findAll().stream().map(ProcedureDTO::from).collect(Collectors.toList());
    }

}
