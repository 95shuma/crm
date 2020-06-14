package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.UserService;
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

@Controller("pkg admin SeniorDoctorController")
@RequestMapping("/admin/senior-doctors")
@AllArgsConstructor
public class SeniorDoctorController {

    private final UserService userService;
    private final PlaceService placeService;
    private final PositionService positionService;
    private final HospitalService hospitalService;

    @GetMapping("/senior-doctor")
    public String regAdminHospital(Model model, Principal principal) {

        userService.checkUserPresence(model, principal);

        model.addAttribute("places",placeService.getAll());
        model.addAttribute("positions", positionService.getAll());
        model.addAttribute("hospitals",hospitalService.getAll());

        return "regAdminHospital";
    }

    @PostMapping("/senior-doctor")
    public String addAdminHospital(@Valid UserRegisterForm userRegisterForm,
                                   BindingResult validationResult,
                                   RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/senior-doctors/senior-doctor";
        }

        userService.createUser(userRegisterForm);

        return "redirect:/admin";
    }
}
