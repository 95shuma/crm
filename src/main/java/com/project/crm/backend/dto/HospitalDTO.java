package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.Hospital;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HospitalDTO {
    private Long id;
    private String name;
    private PlaceDTO place;
    private String address;

    static HospitalDTO from(Hospital hospital) {
        return builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .place(PlaceDTO.from(hospital.getPlace()))
                .address(hospital.getAddress())
                .build();
    }
}
