package com.project.crm.frontend.controller;

import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.model.catalog.Journal;
import com.project.crm.backend.model.catalog.RegistrationType;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.JournalRepo;
import com.project.crm.backend.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class DoctorController {
    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/doctor")
    public String doctorUIPage(Model model, Principal principal){
        return "doctor";
    }
    @GetMapping("/doctorAllAppointment/{inn}")
    public String doctorAllAppointmentPage(Model model, @PathVariable String inn){
        model.addAttribute("journal", journalRepo.findByDoctor(doctorRepo.findByInn(inn).get()).get());
        return "doctorAllAppointment";
    }
    @GetMapping("/patientAllAppointment/{inn}")
    public String patientAllAppointmentPage(Model model, @PathVariable String inn){
        model.addAttribute("journal", journalRepo.findByPatient(patientRepo.findByInn(inn).get()).get());
        return "patientAllAppointment";
    }
    @GetMapping("/doctorAllAppointment")
    public String doctorAllAppointmentPage(Model model){
        return "doctorAllAppointment";
    }
    @GetMapping("/patientAllAppointment")
    public String patientAllAppointmentPage(Model model){
        return "patientAllAppointment";
    }
    @PostMapping("/doctorAllAppointment")
    public String patientAllAppointment(@RequestParam("inn") String inn){
        String text = "redirect:/doctorAllAppointment/" + inn;
        return text;
    }
    @PostMapping("/patientAllAppointment")
    public String doctorAllAppointment(@RequestParam("inn") String inn){
        String text = "redirect:/patientAllAppointment/" + inn;
        return text;
    }
}
