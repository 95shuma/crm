package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class HospitalRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String name; // может иметь номер ЛПУ

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String street;

    @NotNull(message = "Обязательное поле")
    private Long placeId;

    @Positive
    @Pattern(regexp="^\\d+$", message = "Номер дома только из цифр : ${validatedValue}")
    @NotNull(message = "Обязательное поле")
    private String houseNum;
}
