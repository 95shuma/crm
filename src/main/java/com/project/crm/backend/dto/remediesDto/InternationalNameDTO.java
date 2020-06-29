package com.project.crm.backend.dto.remediesDto;


import com.project.crm.backend.model.catalog.remediesCatalog.InternationalName;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InternationalNameDTO {

    private Long id;
    private String name;

    public static InternationalNameDTO from(InternationalName internationalName) {
        return builder()
                .id(internationalName.getId())
                .name(internationalName.getName())
                .build();
    }
}
