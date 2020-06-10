package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RecordJournalRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Size(min = 14, message = "Требуется ввести 14 цифр")
    private String inn = "";

    private String doctorId = "";

    private String patientId = "";

    private String registrarId = "";

    private String hospitalId = "";

    @NotBlank(message = "Обязательное поле")
    private String reason = "";
}
