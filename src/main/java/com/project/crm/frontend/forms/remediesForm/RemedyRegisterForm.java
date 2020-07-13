package com.project.crm.frontend.forms.remediesForm;


import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class RemedyRegisterForm {

    @NotNull(message = "Обязательное поле")
    private Long remedyTypeId;

    @NotNull(message = "Обязательное поле")
    private Long pharmacologicalGroupId;

    @NotNull(message = "Обязательное поле")
    private Long internationalNameId;

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name;

    @NotNull(message = "Обязательное поле")
    private Long dosageId;

    @NotNull(message = "Обязательное поле")
    private Long remediesFormId;
}
