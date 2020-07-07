package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.dto.medicalHistoryCatalogDTO.TreatmentDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Treatment;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.RemedyRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.ProcedureRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.TreatmentRepo;
import com.project.crm.frontend.forms.medicalHistoryForms.TreatmentRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TreatmentService {

    private final TreatmentRepo treatmentRepo;
    private final RemedyRepo remedyRepo;
    private final ProcedureRepo procedureRepo;
    private final MedicalHistoryRepo medicalHistoryRepo;

    public List<TreatmentDTO> getAll(){
        return treatmentRepo.findAll().stream().map(TreatmentDTO::from).collect(Collectors.toList());
    }

    public Page<TreatmentDTO> getAll(Pageable pageable){
        return treatmentRepo.findAll(pageable).map(TreatmentDTO::from);
    }

    public void createTreatment(TreatmentRegisterForm treatmentRegisterForm){
        var treatment = Treatment.builder()
                .remedy(remedyRepo.findById(treatmentRegisterForm.getRemedyId()).get())
                .remediesNote(remedyRepo.findById(treatmentRegisterForm.getRemedyId()).get().getName())
                .type(medicalHistoryRepo.findById(treatmentRegisterForm.getMedicalHistoryId()).get().isTypeOfVisit())
                .procedure(procedureRepo.findById(treatmentRegisterForm.getProcedureId()).get())
                .procedureNote(procedureRepo.findById(treatmentRegisterForm.getProcedureId()).get().getDescription())
                .medicalHistory(medicalHistoryRepo.findById(treatmentRegisterForm.getMedicalHistoryId()).get())
                .build();
        treatmentRepo.save(treatment);
    }
}
