package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RecordJournalRegisterForm {

    private String doctorId = "";

    private String registrarId = "";

    private Long hospitalId = null;

    @NotBlank(message = "Укажите причину")
    private String reason="";
}
