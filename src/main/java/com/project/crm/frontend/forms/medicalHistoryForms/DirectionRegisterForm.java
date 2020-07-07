package com.project.crm.frontend.forms.medicalHistoryForms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DirectionRegisterForm {
    @NotNull(message = "Это поле не может быть пустым")
    private Long labExaminationId;
    @NotNull(message = "Это поле не может быть пустым")
    private Long instrumExaminationId;
    @NotNull(message = "Это поле не может быть пустым")
    private Long positionId;
    @NotNull(message = "Это поле не может быть пустым")
    private Long medicalHistoryId;
}
