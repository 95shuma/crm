package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnoseDTO {

    private Long id;
    private String isdCode;
    private String name;
    private PositionDTO positionDTO;

    public static DiagnoseDTO from(Diagnose diagnose) {
        return builder()
                .id(diagnose.getId())
                .isdCode(diagnose.getIsdCode())
                .name(diagnose.getName())
                .positionDTO(PositionDTO.from(diagnose.getPosition()))
                .build();
    }
}