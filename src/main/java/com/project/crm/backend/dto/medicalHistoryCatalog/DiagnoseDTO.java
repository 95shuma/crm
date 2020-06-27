package com.project.crm.backend.dto.medicalHistoryCatalog;

import com.project.crm.backend.dto.HospitalDTO;
import com.project.crm.backend.dto.PlaceDTO;
import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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