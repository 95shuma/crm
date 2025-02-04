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

    @NotNull(message = "Обязательное поле")
    private Long measureId;

    @NotNull(message = "Обязательное поле")
    private Integer quantity;

}
