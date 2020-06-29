package com.project.crm.backend.dto;


import com.project.crm.backend.dto.medicalHistoryCatalogDTO.*;
import com.project.crm.backend.model.catalog.MedicalHistory;
import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicalHistoryDTO {

    private Long id;
    private RecordJournalDTO recordJournalDTO;
    private Date date;
    private boolean typeOfVisit;
    private String complaint;
    private String recommendation;

    public static MedicalHistoryDTO from(MedicalHistory medicalHistory) {
        return builder()
                .id(medicalHistory.getId())
                .recordJournalDTO(RecordJournalDTO.from(medicalHistory.getRecordJournal()))
                .date(medicalHistory.getDate())
                .typeOfVisit(medicalHistory.isTypeOfVisit())
                .complaint(medicalHistory.getComplaint())
                .recommendation(medicalHistory.getRecommendation())
                .build();
    }
}
