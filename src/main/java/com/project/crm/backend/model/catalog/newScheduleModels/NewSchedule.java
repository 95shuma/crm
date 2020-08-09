package com.project.crm.backend.model.catalog.newScheduleModels;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class NewSchedule {
    private List<String> chosenRegUserList;
    private List<WeekDay> weekDayList;
}
