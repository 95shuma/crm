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
    private final PropertiesService propertiesService;
    private final HospitalService hospitalService;
    private final RoleService roleService;
    private final PositionService positionService;
    private final PlaceService placeService;

    @GetMapping("/doctor")
    public String getDoctor(Model model, Principal principal) {

        userService.checkUserPresence(model, principal);

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new UserRegisterForm());
        }
        model.addAttribute("places", placeService.getAll());
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("positions", positionService.getAll());
        //Constants
        model.addAttribute(Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_SENIOR_DOCTOR);
        model.addAttribute(Constants.ROLE_DOCTOR, Constants.ROLE_DOCTOR);
        model.addAttribute(Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR);
        return "seniorDoctor/doctorController/doctorRegister";
    }
    @GetMapping
    public String getDoctors(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal) {
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(userService.getAllDoctors(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "juniorDoctor/doctorController/doctors";
    }


}
