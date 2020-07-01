package com.project.crm.frontend.forms;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PlaceRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp="^\\d+$", message = "Код места должен содержать только цифры : ${validatedValue}")
    private String codePlace = "";

    @NotNull(message = "Обязательное поле")
    private Integer groupCode = null;

}
