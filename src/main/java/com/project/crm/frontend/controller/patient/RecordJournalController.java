package com.project.crm.frontend.controller.patient;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.RecordJournalService;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
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
import java.util.UUID;

@Controller("pkg patient RecordJournalController")
@RequestMapping("/patient/records")
@AllArgsConstructor
public class RecordJournalController {

    private final HospitalService hospitalService;
    private final RecordJournalService recordJournalService;
    private final RegistrationJournalService registrationJournalService;
    private final UserService userService;

    @GetMapping("/record")
    public String getRecord(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        if (!model.containsAttribute("journal")) {
            model.addAttribute("recordJournalRegisterForm", new RecordJournalRegisterForm());
        }
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("registrationJournals", registrationJournalService.getAll());
        model.addAttribute("users", userService.getAll());

        return "patientAppointment";
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

        userService.checkUserPresence(model, principal);
        model.addAttribute("journal", recordJournalService.getByPatient(userService.getByInn(principal.getName()).getId()));
        return "patientAllAppointment";
    }

    @GetMapping("/recorded")
    public String getRecorded(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("random", UUID.randomUUID());
        return "patientAppointmentCheck";
    }

}
