package com.project.crm.frontend.controller.patient;

import com.project.crm.backend.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final UserService userService;

    @GetMapping
    public String patient(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        return "patient/patient";
    }


}
