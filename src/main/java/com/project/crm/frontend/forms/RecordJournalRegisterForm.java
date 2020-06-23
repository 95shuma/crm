package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RecordJournalRegisterForm {

    private Long doctorId = null;

    private Long registrarId = null;

    private Long hospitalId = null;

    @NotBlank(message = "Укажите причину")
    private String reason="";
}
