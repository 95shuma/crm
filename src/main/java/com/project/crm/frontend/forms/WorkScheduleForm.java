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

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesday_to;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesday_to;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursday_to;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime friday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime friday_to;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturday_to;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sunday_from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sunday_to;
}
