package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Direction;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectionDTO {

    private Long id;
    private LabExaminationDTO labExaminationDTO;
    private InstrumExaminationDTO instrumExaminationDTO;
    private PositionDTO positionDTO;

    public static DirectionDTO from(Direction direction) {
        return builder()
                .id(direction.getId())
                .labExaminationDTO(LabExaminationDTO.from(direction.getLabExamination()))
                .instrumExaminationDTO(InstrumExaminationDTO.from(direction.getInstrumExamination()))
                .positionDTO(PositionDTO.from(direction.getPosition()))
                .build();
    }

}