package com.project.crm.frontend.forms.remediesForm;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PharmacologicalGroupRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name = "";

}
