package com.project.crm.frontend.controller.patient;

import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
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
import java.time.LocalDate;
import java.util.UUID;

@Controller("pkg patient RecordJournalController")
@RequestMapping("/patient/records")
@AllArgsConstructor
public class RecordJournalController {

    private final HospitalService hospitalService;
    private final RecordJournalService recordJournalService;
    private final RegistrationJournalService registrationJournalService;
    private final UserService userService;
    private final WorkScheduleService workScheduleService;

    @GetMapping("/record")
    public String getRecord(Model model, Principal principal){

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);

        if (!model.containsAttribute("journal")) {
            model.addAttribute("recordJournalRegisterForm", new RecordJournalRegisterForm());
        }
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("registrationJournals", registrationJournalService.getAll());
        model.addAttribute("users", userService.getAll());

        return "patient/recordJournalController/patientAppointment";
    }

    @GetMapping("/hospital")
    public String getHospital(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("hospitals", hospitalService.getAll());
        return "patient/recordJournalController/patientAppointmentHospital";
    }

    @PostMapping("/hospital")
    public String recordHospital(RecordJournalRegisterForm recordJournalRegisterForm, RedirectAttributes attributes){
        attributes.addFlashAttribute("hospitalId", recordJournalRegisterForm.getHospitalId());
        return "redirect:/patient/records/doctor";
    }

    @GetMapping("/doctor")
    public String getDoctor(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("doctors", registrationJournalService.getDoctorsByHospitalId(Long.parseLong(model.getAttribute("hospitalId").toString())));
        return "patient/recordJournalController/patientAppointmentDoctor";
    }

    @PostMapping("/doctor")
    public String recordDoctor(RecordJournalRegisterForm recordJournalRegisterForm, RedirectAttributes attributes){
        attributes.addFlashAttribute("hospitalId", recordJournalRegisterForm.getHospitalId());
        attributes.addFlashAttribute("doctorId", recordJournalRegisterForm.getDoctorId());
        attributes.addFlashAttribute("dateTime",workScheduleService.getWorkSchedule(LocalDate.now(),recordJournalRegisterForm.getDoctorId()));

        return "redirect:/patient/records/record";
    }

    @PostMapping
    public String record(@Valid RecordJournalRegisterForm recordJournalRegisterForm,
                                     BindingResult validationResult,
                                     RedirectAttributes attributes, Principal principal){
        attributes.addFlashAttribute("recordJournalRegisterForm", recordJournalRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/patient/records/record";
        }

        recordJournalService.createRecordJournal(recordJournalRegisterForm, principal);

        return "redirect:/patient/records/recorded";

    }
    @GetMapping
    public String getAllRecords(Model model, Principal principal){

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        model.addAttribute("journal", recordJournalService.getByPatient(userService.getByInn(Long.parseLong(principal.getName())).getId()));
        return "patient/recordJournalController/patientAllAppointment";
    }

    @GetMapping("/recorded")
    public String getRecorded(Model model, Principal principal){

        if(principal == null){
            return "errorPage";
        }
        
        userService.checkUserPresence(model, principal);

        model.addAttribute("random", UUID.randomUUID());
        return "patient/recordJournalController/patientAppointmentCheck";
    }

}
