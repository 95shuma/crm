package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller("pkg admin Patient")
@RequestMapping("/admin/patients")
@AllArgsConstructor
public class PatientController {

    private final UserService userService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getPatients(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(userService.getAllPatients(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "seniorDoctor/patientController/patients";
    }
}
