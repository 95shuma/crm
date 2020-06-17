package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HospitalRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String name;

    @NotBlank(message = "Обязательное поле")
    private String street;

    @NotNull(message = "Обязательное поле")
    private Long placeId;

    @NotBlank(message = "Обязательное поле")
    private String houseNum;
}
