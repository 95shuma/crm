package com.project.crm.frontend.forms;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PositionRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String name = "";

}
