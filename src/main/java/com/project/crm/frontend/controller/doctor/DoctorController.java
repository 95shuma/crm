//package com.project.crm.frontend.controller;
//
//import com.project.crm.backend.services.DoctorService;
//import com.project.crm.backend.services.JournalService;
//import com.project.crm.backend.services.PatientService;
//import com.project.crm.backend.services.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.security.Principal;
//
//@Controller
//@AllArgsConstructor
//public class DoctorController {
//
//    private final JournalService journalService;
//    private final DoctorService doctorService;
//    private final PatientService patientService;
//    private final UserService userService;
//
//    @GetMapping("/doctor")
//    public String doctorUIPage(Model model, Principal principal){
//
//        userService.checkUserPresence(model, principal);
//
//        return "doctor";
//    }
//    @GetMapping("/doctorAllAppointment")
//    public String doctorAllAppointmentPage(Model model, Principal principal) {
//
//        userService.checkUserPresence(model, principal);
//        model.addAttribute("journal", journalService.getByDoctor(doctorService.getByInn(principal.getName()).getId()));
//        return "doctorAllAppointment";
//    }
//}
