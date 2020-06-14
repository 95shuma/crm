package com.project.crm.frontend.controller.seniorDoctor;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.PlaceService;
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

@Controller("pkg seniorDoctor Patient")
@RequestMapping("/senior-doctor/patients")
@AllArgsConstructor
public class PatientController {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final PlaceService placeService;

    @GetMapping("/patient")
    public String getPatient(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new UserRegisterForm());
        }
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("places", placeService.getAll());
        return "patientRegister";
    }


    @GetMapping
    public String getPatients(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("patients", userService.getAllPatients());
        return "patients";
    }

    @PostMapping("/patient")
    public String createPatient(@Valid UserRegisterForm userRegisterForm,
                                  BindingResult validationResult,
                                  RedirectAttributes attributes){

        attributes.addFlashAttribute("reg", userRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/senior-doctor/patients/patient";
        }

        userService.createUser(userRegisterForm);

        return "redirect:/senior-doctor";
    }
}
