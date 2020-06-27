package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.RecordJournalService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.project.crm.backend.services.PropertiesService.constructPageable;

@Controller("pkg doctor RecordJournalController")
@RequestMapping("/doctor/records")
@AllArgsConstructor
public class RecordJournalController {

    private final RecordJournalService recordJournalService;
    private final UserService userService;
    private final PropertiesService propertiesService;


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
}
