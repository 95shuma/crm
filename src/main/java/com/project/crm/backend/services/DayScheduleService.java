package com.project.crm.backend.services;


import com.project.crm.backend.model.DaySchedule;
import com.project.crm.backend.model.WorkSchedule;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.model.catalog.newScheduleModels.NewSchedule;
import com.project.crm.backend.repository.DayScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class DayScheduleService {

    private final DayScheduleRepo dayScheduleRepo;

    public void fillThisAndNextWeek(List<WorkSchedule> workScheduleList){
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();           // 1 - 7
        //Заполняем текущую неделю
        for (int i = dayOfWeek.getValue(); i <= 7; i++){
            int finalI = i;
            workScheduleList.stream().forEach(workSchedule -> {
                if (DayOfWeek.values()[finalI-1].getValue() == workSchedule.getDayOfWeek()){
                    if(workSchedule.getDayOfWeek() == (LocalDate.now().getDayOfWeek().getValue()))
                        dayScheduleRepo.save(DaySchedule.builder()
                                .registrationJournal(workSchedule.getRegistrationJournal())
                                .dayTimeStart(LocalDate.now().atTime(workSchedule.getTimeStart()))
                                .dayTimeEnd(LocalDate.now().atTime(workSchedule.getTimeEnd()))
                                .build());
                    else
                        dayScheduleRepo.save(DaySchedule.builder()
                                .registrationJournal(workSchedule.getRegistrationJournal())
                                .dayTimeStart(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.of(workSchedule.getDayOfWeek()))).atTime(workSchedule.getTimeStart()))
                                .dayTimeEnd(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.of(workSchedule.getDayOfWeek()))).atTime(workSchedule.getTimeEnd()))
                                .build());
                }
            });
        }
        //Заполняем следующую неделю.
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        for (int i = dayOfWeek.getValue(); i <= 7; i++){
            int finalI = i;
            workScheduleList.stream().forEach(workSchedule -> {
                if (DayOfWeek.values()[finalI - 1].equals(workSchedule.getDayOfWeek())) {
                    dayScheduleRepo.save(DaySchedule.builder()
                            .registrationJournal(workSchedule.getRegistrationJournal())
                            .dayTimeStart(nextSunday.with(TemporalAdjusters.next(DayOfWeek.of(workSchedule.getDayOfWeek()))).atTime(workSchedule.getTimeStart()))
                            .dayTimeEnd(nextSunday.with(TemporalAdjusters.next(DayOfWeek.of(workSchedule.getDayOfWeek()))).atTime(workSchedule.getTimeEnd()))
                            .build());
                }
            });
        }
    }
}
