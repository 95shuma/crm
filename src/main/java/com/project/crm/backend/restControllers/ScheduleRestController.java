package com.project.crm.backend.restControllers;

import com.project.crm.backend.services.WorkScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ScheduleRestController {

    private final WorkScheduleService workScheduleService;

    @GetMapping("/reg-user/{regUserId}")
    public List<LocalDate> getScheduleByRegUserId(@PathVariable String regUserId){
        return workScheduleService.getWeekScheduleActiveDaysByRegUserId(Long.parseLong(regUserId));
    }
    @GetMapping("/work-day-schedule/{date}&{chosenDoctorId}")
    public List<LocalTime> getWorkDaySchedule(@PathVariable String date, @PathVariable String chosenDoctorId, Principal principal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate chosenDate = LocalDate.parse(date, formatter);
        return workScheduleService.getWorkDayScheduleByDate(chosenDate, Long.parseLong(chosenDoctorId), principal);
    }

}
