package com.project.crm.frontend.controller.seniorDoctor;

import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller("pkg seniorDoctor Doctor")
@RequestMapping("/senior-doctor/doctors")
@AllArgsConstructor
public class DoctorController {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final RoleService roleService;
    private final PositionService positionService;
    private final PlaceService placeService;

    @GetMapping("/doctor")
    public String getDoctor(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new UserRegisterForm());
        }
        model.addAttribute("places", placeService.getAll());
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("positions", positionService.getAll());
        return "seniorDoctor/doctorController/doctorRegister";
    }

    @GetMapping
    public String getDoctors(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("doctors", userService.getAllDoctors());
        return "seniorDoctor/doctorController/doctors";
    }

    @PostMapping("/doctor")
    public String createDoctor(@Valid UserRegisterForm userRegisterForm,
                                 BindingResult validationResult,
                                 RedirectAttributes attributes){
        attributes.addFlashAttribute("reg", userRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/senior-doctor/doctors/doctor";
        }

        userService.createUser(userRegisterForm);

        return "redirect:/senior-doctor";
    }
}
