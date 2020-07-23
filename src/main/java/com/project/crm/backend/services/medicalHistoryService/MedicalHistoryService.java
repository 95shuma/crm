package com.project.crm.backend.services.medicalHistoryService;


import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.dto.RecordJournalDTO;
import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.frontend.forms.medicalHistoryForms.MedicalHistoryRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MedicalHistoryService {

    private final MedicalHistoryRepo medicalHistoryRepo;
    private final RecordJournalRepo recordJournalRepo;

    public void setMedicalHistory(MedicalHistoryRegisterForm medicalHistoryRegisterForm, String record_id){

        MedicalHistory medicalHistory = recordJournalRepo.findById(Long.parseLong(record_id)).get().getMedicalHistory();
        medicalHistory.setRecommendation(medicalHistoryRegisterForm.getRecommendation());
        medicalHistory.setComplaint(medicalHistoryRegisterForm.getComplaint());

        medicalHistoryRepo.save(medicalHistory);
    }

    public List<MedicalHistoryDTO> getAll(){
        return medicalHistoryRepo.findAll().stream().map(MedicalHistoryDTO::from).collect(Collectors.toList());
    }
    public MedicalHistoryDTO getById(String medicalHistoryId){
        return MedicalHistoryDTO.from(medicalHistoryRepo.findById(Long.parseLong(medicalHistoryId)).get());}
}