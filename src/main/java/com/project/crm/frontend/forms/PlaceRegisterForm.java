package com.project.crm.frontend.forms;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlaceRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String name = "";
    @NotBlank(message = "Обязательное поле")
    private String codePlace = "";
    @NotNull(message = "Обязательное поле")
    private Integer groupCode = null;

}
