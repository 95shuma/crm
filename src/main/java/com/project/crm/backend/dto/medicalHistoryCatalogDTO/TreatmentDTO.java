package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.dto.remediesDto.RemedyDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Treatment;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreatmentDTO {

    private Long id;
    private RemedyDTO remedyDTO;
    private String remediesNote;
    private ProcedureDTO procedureDTO;
    private String procedureNote;
    private boolean type;

    public static TreatmentDTO from(Treatment treatment) {
        return builder()
                .id(treatment.getId())
                .remedyDTO(RemedyDTO.from(treatment.getRemedy()))
                .remediesNote(treatment.getRemediesNote())
                .procedureDTO(ProcedureDTO.from(treatment.getProcedure()))
                .procedureNote(treatment.getProcedureNote())
                .type(treatment.isType())
                .build();
    }
}