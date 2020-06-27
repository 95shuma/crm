package com.project.crm.backend.dto.medicalHistoryCatalog;

import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnoseResultDTO {

    private Long id;
    private DiagnoseDTO diagnoseDTO;
    private boolean state;
    private MedicalHistoryDTO medicalHistoryDTO;

    public static DiagnoseResultDTO from(DiagnoseResult diagnoseResult) {
        return builder()
                .id(diagnoseResult.getId())
                .diagnoseDTO(DiagnoseDTO.from(diagnoseResult.getDiagnose()))
                .state(diagnoseResult.isState())
                .medicalHistoryDTO(MedicalHistoryDTO.from(diagnoseResult.getMedicalHistory()))
                .build();
    }
}