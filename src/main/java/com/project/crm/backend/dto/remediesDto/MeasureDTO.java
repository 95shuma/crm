package com.project.crm.backend.dto.remediesDto;


import com.project.crm.backend.model.catalog.remediesCatalog.Measure;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MeasureDTO {

    private Long id;
    private String name;

    public static MeasureDTO from(Measure measure) {
        return builder()
                .id(measure.getId())
                .name(measure.getName())
                .build();
    }
}
