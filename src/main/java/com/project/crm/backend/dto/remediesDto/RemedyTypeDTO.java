package com.project.crm.backend.dto.remediesDto;

import com.project.crm.backend.model.catalog.remediesCatalog.RemedyType;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RemedyTypeDTO {

    private Long id;
    private String name;

    public static RemedyTypeDTO from(RemedyType remedyType) {
        return builder()
                .id(remedyType.getId())
                .name(remedyType.getName())
                .build();
    }
}
