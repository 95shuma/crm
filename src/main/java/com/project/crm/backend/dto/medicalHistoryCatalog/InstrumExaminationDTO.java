package com.project.crm.backend.dto.medicalHistoryCatalog;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.InstrumExamination;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.LabExamination;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstrumExaminationDTO {

    private Long id;
    private String name;
    private String rate;
    private String description;

    public static InstrumExaminationDTO from(InstrumExamination instrumExamination) {
        return builder()
                .id(instrumExamination.getId())
                .name(instrumExamination.getName())
                .rate(instrumExamination.getRate())
                .description(instrumExamination.getDescription())
                .build();
    }

}