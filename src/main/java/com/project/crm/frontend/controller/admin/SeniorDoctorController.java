package com.project.crm.frontend.controller.admin;

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

    @GetMapping("/senior-doctor")
    public String regAdminHospital(Model model, Principal principal) {

        userService.checkUserPresence(model, principal);

        return "regAdminHospital";
    }

    @PostMapping
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