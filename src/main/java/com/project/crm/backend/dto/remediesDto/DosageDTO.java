package com.project.crm.backend.dto.remediesDto;


import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DosageDTO {

    private Long id;
    private String name;
    private MeasureDTO measure;
    private int quantity;


    public static DosageDTO from(Dosage dosage) {
        return builder()
                .id(dosage.getId())
                .name(dosage.getName())
                .measure(MeasureDTO.from(dosage.getMeasure()))
                .build();
    }
}
