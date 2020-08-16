package com.project.crm.frontend.controller.patient;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/patient/")
@AllArgsConstructor
public class ScheduleController {

    private UserService userService;
    private HospitalService hospitalService;
    private RegistrationJournalService registrationJournalService;

    @GetMapping("/schedule")
    public String getSchedule(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("hospitals", hospitalService.getAll());
        return "patient/scheduleController/schedule";
    }

}
