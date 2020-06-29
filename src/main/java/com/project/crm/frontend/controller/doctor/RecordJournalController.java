package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.services.MedicalHistoryService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.RecordJournalService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.MedicalHistoryRegisterForm;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

import static com.project.crm.backend.services.PropertiesService.constructPageable;

@Controller("pkg doctor RecordJournalController")
@RequestMapping("/doctor/records")
@AllArgsConstructor
public class RecordJournalController {

    private final RecordJournalService recordJournalService;
    private final UserService userService;
    private final PropertiesService propertiesService;
    private final MedicalHistoryService medicalHistoryService;


   @GetMapping
   public String getAllRecords(Model model,Principal principal, Pageable pageable, HttpServletRequest uriBuilder) {

       if(principal == null){
           return "errorPage";
       }

       userService.checkUserPresence(model, principal);

       var records = recordJournalService.getPatientsByDoctor(userService.getByInn(Long.parseLong(principal.getName())).getId(),pageable);
       var uri = uriBuilder.getRequestURI();
       constructPageable(records, propertiesService.getDefaultPageSize(), model, uri);

       return "/doctor/appointments/appointments";
   }

    @GetMapping("/today")
    public String getAllRecordsOfToday(Model model,Principal principal, Pageable pageable, HttpServletRequest uriBuilder) {

        if(principal == null){
            return "errorPage";
        }
        userService.checkUserPresence(model, principal);

        var records = recordJournalService.getPatientsByDoctorAndToday
                (userService.getByInn(Long.parseLong(principal.getName())).getId(),pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(records, propertiesService.getDefaultPageSize(), model, uri);

        return "/doctor/appointments/todayAppointments";
    }

    @GetMapping("/{record_id}")
    public String getRecordInfoPage(Model model,Principal principal, @PathVariable("record_id") String recordId) {

        if(principal == null){
            return "errorPage";
        }
        userService.checkUserPresence(model, principal);
        model.addAttribute("journal", recordJournalService.getById(recordId));

        return "/doctor/appointments/appointment";
    }

    @GetMapping("/{record_id}/accept")
    public String getRecordInfoPageAccept(Model model,Principal principal, @PathVariable("record_id") String record_id) {

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        model.addAttribute("journal", recordJournalService.getById(record_id));
        //model.addAttribute("medicalHistory", medicalHistoryService.getByRecordJournalId(record_id));

        return "/doctor/appointments/appointmentAccept";
    }

    @PostMapping("/{record_id}/accept")
    public String createMedicalHistory(@Valid MedicalHistoryRegisterForm medicalHistoryRegisterForm,
                                       BindingResult validationResult,
                                       RedirectAttributes attributes, @PathVariable("record_id") String record_id){

        attributes.addFlashAttribute("medicalHistoryRegisterForm", medicalHistoryRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/records/" + record_id + "/accept";
        }

        medicalHistoryService.createMedicalHistory(medicalHistoryRegisterForm, record_id);

        return "redirect:/doctor/records";
    }

}
