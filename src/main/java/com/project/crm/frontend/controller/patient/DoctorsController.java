package com.project.crm.frontend.controller.patient;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class DoctorsController {

    private UserService userService;
    private HospitalService hospitalService;
    private RegistrationJournalService registrationJournalService;

    @GetMapping("/hospitals")
    public String getHospital(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("hospitals", hospitalService.getAll());
        return "patient/doctorsController/chooseHospital";
    }

    @PostMapping("/hospital")
    public String recordHospital(@RequestParam("hospitalId") Long hospitalId, RedirectAttributes attributes){
        attributes.addFlashAttribute("hospitalId", hospitalId);
        return "redirect:/patient/hospital/doctors";
    }

    @GetMapping("/hospital/doctors")
    public String getDoctor(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("doctors", registrationJournalService.getDoctorsByHospitalId((Long) model.getAttribute("hospitalId")));
        return "patient/doctorsController/doctorss";
    }

}
