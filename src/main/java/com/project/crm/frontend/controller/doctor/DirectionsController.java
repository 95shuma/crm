package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.*;
import com.project.crm.backend.services.medicalHistoryService.DirectionService;
import com.project.crm.backend.services.medicalHistoryService.InstrumExaminationService;
import com.project.crm.backend.services.medicalHistoryService.LabExaminationService;
import com.project.crm.backend.services.medicalHistoryService.MedicalHistoryService;
import com.project.crm.frontend.forms.medicalHistoryForms.DirectionRegisterForm;
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

@Controller
@RequestMapping("/doctor/directions")
@AllArgsConstructor
public class DirectionsController {

    private final RecordJournalService recordJournalService;
    private final UserService userService;
    private final DirectionService directionService;
    private final LabExaminationService labExaminationService;
    private final InstrumExaminationService instrumExaminationService;
    private final PositionService positionService;
    private final MedicalHistoryService medicalHistoryService;
    private final PropertiesService propertiesService;

    @GetMapping("/{medicalHistory_id}/direction")
    public String getRecordJournalDirection(Model model,Principal principal, @PathVariable("medicalHistory_id") Long medicalHistory_id) {

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        model.addAttribute("labExamination",labExaminationService.getAll());
        model.addAttribute("instrumExamination",instrumExaminationService.getAll());
        model.addAttribute("position",positionService.getAll());
        model.addAttribute("medicalHistory_id", medicalHistory_id);

        return "/doctor/directionController/directionRegisterWithRecordJournal";
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
        return "/doctor/directionController/directions";
    }

    @PostMapping
    public String createDirection(@Valid DirectionRegisterForm directionRegisterForm, BindingResult validationResult,
                                  RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/directions/"+directionRegisterForm.getMedicalHistoryId()+"/direction";
        }

        directionService.createDirection(directionRegisterForm);

        return "redirect:/doctor/directions/"+directionRegisterForm.getMedicalHistoryId()+"/directions";
    }

}
