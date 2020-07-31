package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.WorkScheduleService;
import com.project.crm.frontend.forms.WorkScheduleForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

import static com.project.crm.backend.services.PropertiesService.constructPageable;

@Controller("pkg doctor WorkScheduleController")
@RequestMapping("/doctor/schedules")
@AllArgsConstructor
public class WorkScheduleController {

    private final UserService userService;
    private final WorkScheduleService workScheduleService;
    private final RegistrationJournalService registrationJournalService;
    private final PropertiesService propertiesService;

    /*
    @GetMapping
    public String getSchedules(Model model, Principal principal, Pageable pageable, HttpServletRequest uriBuilder){
        userService.checkUserPresence(model, principal);
        constructPageable(workScheduleService.getWorkSchedules(Long.parseLong(principal.getName()),pageable),propertiesService.getDefaultPageSize(),model,uriBuilder.getRequestURI());
        return "doctor/scheduleController/schedules";
    }

    @GetMapping("schedule")
    public String getSchedule(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("regJournalId",registrationJournalService.getRegJournalId(Long.parseLong(principal.getName())));
        return "doctor/scheduleController/scheduleRegister";
    }

    @PostMapping("schedule")
    public String createSchedule(@Valid WorkScheduleForm workScheduleForm,BindingResult validationResult,RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/schedules/schedule";
        }
        workScheduleService.createWorkSchedule(workScheduleForm);
        return "redirect:";
    }*/
}