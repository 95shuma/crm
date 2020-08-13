package com.project.crm.frontend.controller.juniorDoctor;

import com.project.crm.backend.services.*;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller("pkg juniorDoctor Doctor")
@RequestMapping("/junior-doctor/doctors")
@AllArgsConstructor
public class DoctorController {

    private final UserService userService;
    private final RegistrationJournalService registrationJournalService;
    private final PropertiesService propertiesService;


    @GetMapping
    public String getDoctors(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal) {
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(registrationJournalService.getAllHospitalStaff(pageable, principal), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "juniorDoctor/doctorController/doctors";
    }

    @GetMapping("/doctor/{id}")
    public String getOneDoctor(@PathVariable Long id, Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("doctor", userService.getUserById(id));
        return "/juniorDoctor/doctorController/aboutDoctor";
    }
}
