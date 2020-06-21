package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.Hospital;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HospitalDTO {

    private Long id;

    @NotBlank(message = "Обязательное поле")
    private String name;
    
    private PlaceDTO place;

    @NotBlank(message = "Обязательное поле")
    private String address;

    public static HospitalDTO from(Hospital hospital) {
        return builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .place(PlaceDTO.from(hospital.getPlace()))
                .address(hospital.getAddress())
                .build();
    }
}
