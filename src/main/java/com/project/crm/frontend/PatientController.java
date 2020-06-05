package com.project.crm.frontend;

import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.Doc;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class PatientController {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private RegistrationTypeRepo registrationTypeRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private HospitalsDoctorRepo hospitalsDoctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/patient")
    public String patientUIPage(Model model){
        return "patient";
    }
    @GetMapping("/patientAppointment")
    public String patientAppointmentPage(Model model){
        List<Hospital> hospitals = hospitalRepo.findAll();
        List<RegistrationType> registrationTypes = registrationTypeRepo.findAll();
        List<HospitalsDoctor> hospitalsDoctors = hospitalsDoctorRepo.findAll();

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
                .doctor(doctorRepo.findByName(journalRegisterForm.getDoctor()))
                .hospital(hospitalRepo.findByName(journalRegisterForm.getHospital()))
                .patient(patientRepo.findByInn(journalRegisterForm.getInn()).get())
                .registration_type(registrationTypeRepo.findByName(journalRegisterForm.getRegistration_type()))
                .reason(journalRegisterForm.getReason())
                .dateTime(LocalDateTime.now())
                .build();
        journalRepo.save(journal);

        return "redirect:/patientAppointment";

    }
}
