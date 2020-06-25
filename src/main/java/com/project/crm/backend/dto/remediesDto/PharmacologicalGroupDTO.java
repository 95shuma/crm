package com.project.crm.backend.dto.remediesDto;



import com.project.crm.backend.model.catalog.remediesCatalog.PharmacologicalGroup;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PharmacologicalGroupDTO {

    private Long id;
    private String name;

    public static PharmacologicalGroupDTO from(PharmacologicalGroup pharmacologicalGroup) {
        return builder()
                .id(pharmacologicalGroup.getId())
                .name(pharmacologicalGroup.getName())
                .build();
    }
}
