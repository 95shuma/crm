package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.ExaminationResult;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExaminationResultDTO {

    private Long id;
    private LabExaminationDTO labExaminationDTO;
    private String labExaminationResult;
    private InstrumExaminationDTO instrumExaminationDTO;
    private String instrumExaminationResult;
    private String generalState;
    private MedicalHistoryDTO medicalHistoryDTO;

    public static ExaminationResultDTO from(ExaminationResult examinationResult) {
        return builder()
                .id(examinationResult.getId())
                .labExaminationDTO(LabExaminationDTO.from(examinationResult.getLabExamination()))
                .labExaminationResult(examinationResult.getLabExaminationResult())
                .instrumExaminationDTO(InstrumExaminationDTO.from(examinationResult.getInstrumExamination()))
                .instrumExaminationResult(examinationResult.getInstrumExaminationResult())
                .generalState(examinationResult.getGeneralState())
                .medicalHistoryDTO(MedicalHistoryDTO.from(examinationResult.getMedicalHistory()))
                .build();
    }

}