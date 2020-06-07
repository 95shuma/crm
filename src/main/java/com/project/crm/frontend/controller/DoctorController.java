package com.project.crm.frontend.controller;

import com.project.crm.backend.services.DoctorService;
import com.project.crm.backend.services.JournalService;
import com.project.crm.backend.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class DoctorController {

    private final JournalService journalService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @GetMapping("/doctor")
    public String doctorUIPage(Model model, Principal principal){
        return "doctor";
    }
    @GetMapping("/doctorAllAppointment/{inn}")
    public String doctorAllAppointmentPage(Model model, @PathVariable String inn){
        model.addAttribute("journal", journalService.getByDoctor(doctorService.getByInn(inn).getId()));
        return "doctorAllAppointment";
    }
    @GetMapping("/patientAllAppointment/{inn}")
    public String patientAllAppointmentPage(Model model, @PathVariable String inn){
        model.addAttribute("journal", journalService.getByPatient(patientService.getByInn(inn).getId()));
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
        return "redirect:/doctorAllAppointment/" + inn;
    }
    @PostMapping("/patientAllAppointment")
    public String doctorAllAppointment(@RequestParam("inn") String inn){
        return "redirect:/patientAllAppointment/" + inn;
    }
}
