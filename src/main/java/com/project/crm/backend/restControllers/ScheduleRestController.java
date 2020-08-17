package com.project.crm.backend.restControllers;

import com.project.crm.backend.services.WorkScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ScheduleRestController {

    private final WorkScheduleService workScheduleService;

    @GetMapping("/reg-user/{regUserId}")
    public List<LocalDate> getScheduleByRegUserId(@PathVariable String regUserId){
        return workScheduleService.getWeekScheduleActiveDaysByRegUserId(Long.parseLong(regUserId.replaceAll("\\s+","")));
    }

}
