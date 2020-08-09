package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class NewScheduleForm {
    @NotNull(message = "Отметьте врача/врачей, к которым необходимо создать график")
    private List<String> chosenRegUser;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturdayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturdayTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sundayFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sundayTo;
}
