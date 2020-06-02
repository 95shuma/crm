package com.project.crm.frontend;

import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class FrontController {
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error, Principal principal) {

        model.addAttribute("error", error);
        if (principal != null){
            var user = patientRepo.getOne(Long.parseLong(principal.getName()));
        }
        return "login";
    }
    @GetMapping("/default")
    public String defaultPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model, Principal principal) {
        model.addAttribute("error", error);
        if(principal != null){
            model.addAttribute("isLoggedIn", true);
            if (doctorRepo.existsByInn(principal.getName())){
                return "redirect:/doctor";
            } else {
                return "redirect:/patient";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/patient")
    public String prPage(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error, Principal principal) {

        model.addAttribute("error", error);
        if (principal != null){
            var user = patientRepo.getOne(Long.parseLong(principal.getName()));
        }
        return "patient";
    }
    @GetMapping("/doctor")
    public String dctPage(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error, Principal principal) {

        model.addAttribute("error", error);
        if (principal != null){
            var user = patientRepo.getOne(Long.parseLong(principal.getName()));
        }
        return "doctor";
    }
}
