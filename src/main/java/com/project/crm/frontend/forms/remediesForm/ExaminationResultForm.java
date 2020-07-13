package com.project.crm.frontend.forms.remediesForm;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ExaminationResultForm {

    @NotNull(message = "Обязательное поле")
    private Long labExaminationId;

    @NotBlank(message = "Обязательное поле")
    private String labExaminationResult;

    @NotNull(message = "Обязательное поле")
    private Long instrumExaminationId;

    @NotBlank(message = "Обязательное поле")
    private String instrumExaminationResult;

    @NotBlank(message = "Обязательное поле")
    private String generalState;

    @NotNull(message = "Обязательное поле")
    private Long medicalHistoryId;
}
