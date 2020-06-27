package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.LabExamination;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LabExaminationDTO {

    private Long id;
    private String name;
    private String rate;

    public static LabExaminationDTO from(LabExamination labExamination) {
        return builder()
                .id(labExamination.getId())
                .name(labExamination.getName())
                .rate(labExamination.getRate())
                .build();
    }
}