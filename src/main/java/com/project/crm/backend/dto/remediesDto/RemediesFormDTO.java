package com.project.crm.backend.dto.remediesDto;


import com.project.crm.backend.model.catalog.remediesCatalog.RemediesForm;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RemediesFormDTO {

    private Long id;
    private String name;

    public static RemediesFormDTO from(RemediesForm remediesForm) {
        return builder()
                .id(remediesForm.getId())
                .name(remediesForm.getName())
                .build();
    }
}
