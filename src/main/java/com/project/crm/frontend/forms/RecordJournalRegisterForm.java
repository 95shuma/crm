package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RecordJournalRegisterForm {

    @NotNull(message = "NotNull - doctor")
    private Long doctorId = null;

    private Long registrarId = null;

    @Future(message = "Нельзя записаться в прошлое")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    @NotNull(message = "NotNull - hospital")
    private Long hospitalId = null;

    @NotBlank(message = "Укажите причину")
    private String reason="";
}
