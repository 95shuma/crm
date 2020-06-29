package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Procedure;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProcedureDTO {

    private Long id;
    private String name;
    private String description;

    public static ProcedureDTO from(Procedure procedure) {
        return builder()
                .id(procedure.getId())
                .name(procedure.getName())
                .description(procedure.getDescription())
                .build();
    }

}