package com.project.crm.backend.dto;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.WorkSchedule;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkScheduleDTO {

    private Long id;
    private RegistrationJournal registrationJournal;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private int dayOfWeek;

    public static WorkScheduleDTO from(WorkSchedule workSchedule) {
        return builder()
                .id(workSchedule.getId())
                .registrationJournal(workSchedule.getRegistrationJournal())
                .timeStart(workSchedule.getTimeStart())
                .timeEnd(workSchedule.getTimeEnd())
                .dayOfWeek(workSchedule.getDayOfWeek())
                .build();
    }
    public static List<WorkScheduleDTO> listFrom(List<WorkSchedule> objList){
        List<WorkScheduleDTO> listDto = new ArrayList<>();
        objList.forEach(obj -> {
            listDto.add(WorkScheduleDTO.from(obj));
        });
        return listDto;
    }
}
