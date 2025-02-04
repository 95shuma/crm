package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.*;
import com.project.crm.backend.services.medicalHistoryService.DirectionService;
import com.project.crm.backend.services.medicalHistoryService.InstrumExaminationService;
import com.project.crm.backend.services.medicalHistoryService.LabExaminationService;
import com.project.crm.backend.services.medicalHistoryService.MedicalHistoryService;
import com.project.crm.frontend.forms.medicalHistoryForms.MedicalHistoryRegisterForm;
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

    private final DirectionService directionService;
    private final RecordJournalService recordJournalService;
    private final UserService userService;
    private final PropertiesService propertiesService;
    private final LabExaminationService labExaminationService;
    private final InstrumExaminationService instrumExaminationService;
    private final PositionService positionService;
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
        model.addAttribute("patient", recordJournalService.getById(record_id));

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

        medicalHistoryService.setMedicalHistory(medicalHistoryRegisterForm, record_id);

        return "redirect:/doctor/records";
    }

    @GetMapping("/{medicalHistory_id}")
    public String getMedicalHistory(Model model,Principal principal, @PathVariable("medicalHistory_id") String medicalHistory_id) {

        if(principal == null){
            return "errorPage";
        }
        userService.checkUserPresence(model, principal);
        model.addAttribute("journal", medicalHistoryService.getById(medicalHistory_id));

        return "/doctor/appointments/appointmentResult";
    }

    @GetMapping("/{record_id}/result")
    public String getRecordInfoPageResult(Model model,Principal principal, @PathVariable("record_id") String record_id) {

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        model.addAttribute("patient", recordJournalService.getById(record_id));

        return "/doctor/appointments/appointmentResult";
    }

    @GetMapping("/{medicalHistory}/directions")
    public String getRecordJournalDirections(Model model,Principal principal,HttpServletRequest uriBuilder, @PathVariable("medicalHistory") Long medicalHistory, Pageable pageable) {

        if (principal == null) {
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        var directions = directionService.getAll(pageable, (medicalHistory));
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(directions, propertiesService.getDefaultPageSize(), model, uri);
        model.addAttribute("medicalHistory_id", medicalHistory);
        return "/doctor/appointments/appointmentResult";
    }
    @GetMapping("/patients/{patientId}")
    public String getAcceptedPatients(@PathVariable String patientId, Model model, Principal principal, HttpServletRequest uriBuilder, Pageable pageable) {

        if(principal == null){
            return "errorPage";
        }
        userService.checkUserPresence(model, principal);
        model.addAttribute("patient", userService.getById(Long.parseLong(patientId)));
        PropertiesService.constructPageable(recordJournalService.getByPatientId(Long.parseLong(patientId), pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "/doctor/appointments/patientAppointment";
    }

    @GetMapping("/patients/accepted")
    public String getAcceptedPatients(Model model, Principal principal, HttpServletRequest uriBuilder, Pageable pageable) {

        if(principal == null){
            return "errorPage";
        }
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(recordJournalService.getAcceptedPatients(userService.getUserByInn(Long.parseLong(principal.getName())), pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "/doctor/appointments/myPatientsAppointment";
    }
}
