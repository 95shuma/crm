package com.project.crm.backend.dto.medicalHistoryCatalogDTO;

import com.project.crm.backend.dto.MedicalHistoryDTO;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.SickList;
import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SickListDTO {

    private Long id;
    private Long number;
    private Date startDate;
    private Date endDate;
    private MedicalHistoryDTO medicalHistoryDTO;

    public static SickListDTO from(SickList sickList) {
        return builder()
                .id(sickList.getId())
                .number(sickList.getNumber())
                .startDate(sickList.getStartDate())
                .endDate(sickList.getEndDate())
                .medicalHistoryDTO(MedicalHistoryDTO.from(sickList.getMedicalHistory()))
                .build();
    }

}