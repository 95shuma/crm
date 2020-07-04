package com.project.crm.backend.services;


import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.frontend.forms.MedicalHistoryRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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


}