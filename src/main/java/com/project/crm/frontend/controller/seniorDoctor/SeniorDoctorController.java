package com.project.crm.frontend.controller.seniorDoctor;

import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller("pkg seniorDoctor SeniorDoctorController")
@RequestMapping("/senior-doctor")
@AllArgsConstructor
public class SeniorDoctorController {

    private final UserService userService;

    @GetMapping
    public String adminHCFPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        return "seniorDoctor/seniorDoctor";
    }

//    @GetMapping("/senior-doctor")
//    public String regAdminHospital(Model model, Principal principal) {
//
//        userService.checkUserPresence(model, principal);
//
//        return "regAdminHospital";
//    }
//
//    @PostMapping("/senior-doctor")
//    public String addAdminHospital(@Valid UserRegisterForm userRegisterForm,
//                                   BindingResult validationResult,
//                                   RedirectAttributes attributes){
//        if (validationResult.hasFieldErrors()) {
//            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
//            return "redirect:/senior-doctor";
//        }
//
//        userService.createUser(userRegisterForm);
//
//        return "redirect:/admin";
//    }
}
