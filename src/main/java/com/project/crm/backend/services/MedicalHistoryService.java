package com.project.crm.backend.services;


import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.dto.RecordJournalDTO;
import com.project.crm.backend.dto.remediesDto.DosageDTO;
import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.RecordJournal;
import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import com.project.crm.backend.repository.DosageRepo;
import com.project.crm.backend.repository.MeasureRepo;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import com.project.crm.frontend.forms.DosageRegisterForm;
import com.project.crm.frontend.forms.MedicalHistoryRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


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