package com.project.crm.backend.dto;


import com.project.crm.backend.model.catalog.Position;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionDTO {
    private Long id;
    private String name;


    static PositionDTO from(Position position) {
        return builder()
                .id(position.getId())
                .name(position.getName())
                .build();
    }
}
