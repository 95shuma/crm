package com.project.crm.frontend.controller;

import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.JournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
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
        List<Hospital> hospitals = hospitalService.getAll();
        List<RegistrationType> registrationTypes = registrationTypeService.getAll();
        List<HospitalsDoctor> hospitalsDoctors = hospitalsDoctorService.getAll();

        if (!model.containsAttribute("journal")) {
            model.addAttribute("journal", new JournalRegisterForm());
        }
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("registrationTypes", registrationTypes);
        model.addAttribute("hospitalsDoctors", hospitalsDoctors);

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
        var journal = Journal.builder()
                .doctor(doctorService.getByName(journalRegisterForm.getDoctor()))
                .hospital(hospitalService.getByName(journalRegisterForm.getHospital()))
                .patient(patientService.getByInn(journalRegisterForm.getInn()).get())
                .registration_type(registrationTypeService.getByName(journalRegisterForm.getRegistration_type()))
                .reason(journalRegisterForm.getReason())
                .dateTime(LocalDateTime.now())
                .build();
        journalService.save(journal);

        return "redirect:/patientAppointmentCheck";

    }
    @GetMapping("/patientAppointmentCheck")
    public String patientAppointmentCheckPage(Model model){
        UUID uuid = UUID.randomUUID();
        model.addAttribute("random", uuid);
        return "patientAppointmentCheck";
    }
}
