package com.project.crm.frontend.controller;

import com.project.crm.backend.services.DoctorService;
import com.project.crm.backend.services.JournalService;
import com.project.crm.backend.services.PatientService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class DoctorController {

    private final JournalService journalService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserService userService;

    @GetMapping("/doctor")
    public String doctorUIPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        return "doctor";
    }
    @GetMapping("/doctorAllAppointment/{inn}")
    public String doctorAllAppointmentPage(Model model, @PathVariable String inn,
                                           Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("journal", journalService.getByDoctor(doctorService.getByInn(inn).getId()));
        return "doctorAllAppointment";
    }
    @GetMapping("/patientAllAppointment/{inn}")
    public String patientAllAppointmentPage(Model model, @PathVariable String inn,
                                            Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("journal", journalService.getByPatient(patientService.getByInn(inn).getId()));
        return "patientAllAppointment";
    }
    @GetMapping("/doctorAllAppointment")
    public String doctorAllAppointmentPage(Model model, Principal principal) {

        userService.checkUserPresence(model, principal);

        return "doctorAllAppointment";
    }
    @GetMapping("/patientAllAppointment")
    public String patientAllAppointmentPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

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
