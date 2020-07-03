package com.project.crm.frontend.forms;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class InternationalNameRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name = "";

}
