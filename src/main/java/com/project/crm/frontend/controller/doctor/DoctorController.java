package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller("pkg doctor Doctor")
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

    private final UserService userService;

    @GetMapping
    public String doctor(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        return "doctor/doctor";
    }
}
