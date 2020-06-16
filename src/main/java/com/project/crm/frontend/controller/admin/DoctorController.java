package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller("pkg seniorDoctor Doctor")
@RequestMapping("/admin/doctors")
@AllArgsConstructor
public class DoctorController {

    private final UserService userService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getDoctors(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(userService.getAllDoctors(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "seniorDoctor/doctorController/doctors";
    }
}
