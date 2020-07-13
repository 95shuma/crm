package com.project.crm.frontend.forms.medicalHistoryForms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TreatmentRegisterForm {

    @NotNull(message = "Обязательное поле")
    private Long remedyId;

    @NotNull(message = "Обязательное поле")
    private Long procedureId;

    @NotNull(message = "Обязательное поле")
    private Long medicalHistoryId;
}
