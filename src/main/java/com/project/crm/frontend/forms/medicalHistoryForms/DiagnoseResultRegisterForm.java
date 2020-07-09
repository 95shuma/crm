package com.project.crm.frontend.forms.medicalHistoryForms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DiagnoseResultRegisterForm {
    @NotNull(message = "Это поле не может быть пустым")
    private Long diagnoseId;
    @NotBlank(message = "Выберите один из вариантов")
    private String state;
    @NotNull(message = "Это поле не может быть пустым")
    private Long medicalHistoryId;
}
