package com.project.crm.frontend.controller;

import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.services.AdministratorService;
import com.project.crm.backend.services.DoctorService;
import com.project.crm.backend.services.HospitalsDoctorService;
import com.project.crm.backend.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class LoginController {
    private final AdministratorService administratorService;
    private final HospitalsDoctorService hospitalsDoctorService;

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error, Principal principal) {
        model.addAttribute("error", error);
        return "login";
    }
    @GetMapping("/default")
    public String defaultPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model, Principal principal) {
        model.addAttribute("error", error);
        if(principal != null){
            model.addAttribute("isLoggedIn", true);
            if (administratorService.existByInn(principal.getName())) {
                return "redirect:/admin";
            } else if (hospitalsDoctorService.existByInnForAdminHCF(principal.getName())){
                return "redirect:/adminHCF";
            } else if (hospitalsDoctorService.existByInnForDoctor(principal.getName())){
                return "redirect:/doctor";
            } else {
                return "redirect:/patient";
            }
        } else {
            return "redirect:/login";
        }
    }
}
