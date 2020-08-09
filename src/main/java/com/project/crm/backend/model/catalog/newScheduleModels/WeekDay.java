package com.project.crm.backend.model.catalog.newScheduleModels;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class WeekDay {
    private DayOfWeek dayOfWeek;
    private LocalTime from;
    private LocalTime to;
}
