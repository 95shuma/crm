package com.project.crm.frontend.forms.remediesForm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class RecordJournalRegisterFormNew {

    private Long doctorId = null;

    private String time = null;

    private String date = null;

    private Long hospitalId = null;

    private String reason="";
}
