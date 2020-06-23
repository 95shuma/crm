package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.Place;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceDTO {

    private Long id;
    @NotBlank(message = "Обязательное поле")
    private String name;
    @NotBlank(message = "Обязательное поле")
    private String codePlace;
    @NotNull(message = "Обязательное поле")
    private Integer groupCode;

    public static PlaceDTO from(Place place) {
        return builder()
                .id(place.getId())
                .name(place.getName())
                .codePlace(place.getCodePlace().toString())
                .groupCode(place.getGroupCode())
                .build();
    }
}
