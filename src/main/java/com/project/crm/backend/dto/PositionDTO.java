package com.project.crm.backend.dto;


import com.project.crm.backend.model.catalog.Position;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionDTO {

    private Long id;
    @NotBlank(message = "Обязательное поле")
    private String name;


    public static PositionDTO from(Position position) {
        return builder()
                .id(position.getId())
                .name(position.getName())
                .build();
    }
}
