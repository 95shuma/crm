package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.Place;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceDTO {
    private Long id;
    private String name;
    private String codePlace;
    private Integer groupCode;

    static PlaceDTO from(Place place) {
        return builder()
                .id(place.getId())
                .name(place.getName())
                .codePlace(place.getCodePlace())
                .groupCode(place.getGroupCode())
                .build();
    }
}
