package com.project.crm.frontend;

import com.project.crm.backend.services.AdministratorService;
import com.project.crm.backend.services.DoctorService;
import com.project.crm.backend.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class FrontController {
    private final AdministratorService administratorService;
    private final DoctorService doctorService;
    private final PatientService patientService;

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
            if (administratorService.existByInn(principal.getName())){
                return "redirect:/admin";
            } else if (doctorService.existByInn(principal.getName())){
                return "redirect:/doctor";
            } else {
                return "redirect:/patient";
            }
        } else {
            return "redirect:/login";
        }
    }
}