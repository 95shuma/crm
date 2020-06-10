package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.RecordJournalService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller("pkg doctor RecordJournalController")
@RequestMapping("/doctor/records")
@AllArgsConstructor
public class RecordJournalController {

    private final RecordJournalService recordJournalService;
    private final UserService userService;

    @GetMapping
    public String getAllRecords(Model model, Principal principal) {

        userService.checkUserPresence(model, principal);
        model.addAttribute("journal", recordJournalService.getByDoctor(userService.getByInn(principal.getName()).getId()));
        return "doctorAllAppointment";
    }

}
