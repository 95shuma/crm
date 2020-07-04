package com.project.crm.frontend.forms.remediesForm;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DosageRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String name;

    @NotBlank(message = "Обязательное поле")
    private String street;

    @NotNull(message = "Обязательное поле")
    private Long measureId;

    @NotBlank(message = "Обязательное поле")
    private int quantity;

}
