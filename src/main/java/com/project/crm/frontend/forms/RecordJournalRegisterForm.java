package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RecordJournalRegisterForm {

    private String doctorId = "";

    private String registrarId = "";

    private Long hospitalId = null;

    @NotBlank(message = "Обязательное поле")
    private String reason = "";
}
