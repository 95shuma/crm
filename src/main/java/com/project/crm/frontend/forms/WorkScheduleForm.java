package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class WorkScheduleForm {
    private List<String> chosenRegUser;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime monday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime monday_to;

}
