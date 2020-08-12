package com.project.crm.frontend.controller.juniorDoctor;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.PatientRegisterForm;
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

@Controller("pkg juniorDoctor Patient")
@RequestMapping("/junior-doctor/patients")
@AllArgsConstructor
public class PatientController {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final PlaceService placeService;
    private final PropertiesService propertiesService;

    @GetMapping("/patient")
    public String getPatient(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new UserRegisterForm());
        }
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("places", placeService.getAll());
        return "juniorDoctor/patientController/patientRegister";
    }
    @GetMapping
    public String getPatients(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(userService.getAllPatients(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "juniorDoctor/patientController/patients";
    }
    @PostMapping("/patient")
    public String createPatient(@Valid PatientRegisterForm patientRegisterForm,
                                  BindingResult validationResult,
                                  RedirectAttributes attributes){
        attributes.addFlashAttribute("reg", patientRegisterForm);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/junior-doctor/patients/patient";
        }
        userService.createUserFormPatientForm(patientRegisterForm);
        return "redirect:/junior-doctor";
    }
}
