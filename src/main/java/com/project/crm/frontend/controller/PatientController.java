package com.project.crm.frontend.controller;

import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.JournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping
@AllArgsConstructor
public class PatientController {

    private final JournalService journalService;
    private final HospitalService hospitalService;
    private final RegistrationTypeService registrationTypeService;
    private final DoctorService doctorService;
    private final HospitalsDoctorService hospitalsDoctorService;
    private final PatientService patientService;

    @GetMapping("/patient")
    public String patientUIPage(Model model){
        return "patient";
    }
    @GetMapping("/patientAppointment")
    public String patientAppointmentPage(Model model){

        if (!model.containsAttribute("journal")) {
            model.addAttribute("journal", new JournalRegisterForm());
        }
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("registrationTypes", registrationTypeService.getAll());
        model.addAttribute("hospitalsDoctors", hospitalsDoctorService.getAll());

        return "patientAppointment";
    }
    @PostMapping("/patientAppointment")
    public String patientAppointment(@Valid JournalRegisterForm journalRegisterForm,
                                     BindingResult validationResult,
                                     RedirectAttributes attributes){
        attributes.addFlashAttribute("journalRegisterForm", journalRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/patientAppointment";
        }

        journalService.save(journalRegisterForm);

        return "redirect:/patientAppointmentCheck";

    }
    @GetMapping("/patientAppointmentCheck")
    public String patientAppointmentCheckPage(Model model){
        model.addAttribute("random", UUID.randomUUID());
        return "patientAppointmentCheck";
    }
}
