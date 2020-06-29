package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnoseResultDTO {

    private Long id;
    private DiagnoseDTO diagnoseDTO;
    private boolean state;

    public static DiagnoseResultDTO from(DiagnoseResult diagnoseResult) {
        return builder()
                .id(diagnoseResult.getId())
                .diagnoseDTO(DiagnoseDTO.from(diagnoseResult.getDiagnose()))
                .state(diagnoseResult.isState())
                .build();
    }
}