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
    private DirectionDTO directionDTO;
    private ExaminationResultDTO examinationResultDTO;
    private DiagnoseResultDTO diagnoseResultDTO;
    private String recommendation;
    private TreatmentDTO treatmentDTO;
    private SickListDTO sickListDTO;

    public static MedicalHistoryDTO from(MedicalHistory medicalHistory) {
        return builder()
                .id(medicalHistory.getId())
                .recordJournalDTO(RecordJournalDTO.from(medicalHistory.getRecordJournal()))
                .date(medicalHistory.getDate())
                .typeOfVisit(medicalHistory.isTypeOfVisit())
                .complaint(medicalHistory.getComplaint())
                .directionDTO(DirectionDTO.from(medicalHistory.getDirection()))
                .examinationResultDTO(ExaminationResultDTO.from(medicalHistory.getExaminationResult()))
                .diagnoseResultDTO(DiagnoseResultDTO.from(medicalHistory.getDiagnoseResult()))
                .recommendation(medicalHistory.getRecommendation())
                .treatmentDTO(TreatmentDTO.from(medicalHistory.getTreatment()))
                .sickListDTO(SickListDTO.from(medicalHistory.getSickList()))
                .build();
    }
}
