package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.RecordJournalService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.medicalHistoryService.*;
import com.project.crm.frontend.forms.medicalHistoryForms.DiagnoseResultRegisterForm;
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
@RequestMapping("/doctor/diagnoseResult")
@AllArgsConstructor
public class DiagnoseResultController {

    private final DiagnoseService diagnoseService;
    private final DiagnoseResultService diagnoseResultService;
    private final UserService userService;
    private final DirectionService directionService;
    private final PropertiesService propertiesService;

    @GetMapping("/{medicalHistory_id}/diagnoseResult")
    public String getDiagnosisResult(Model model,Principal principal, @PathVariable("medicalHistory_id") Long medicalHistory_id) {

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        model.addAttribute("diagnose", diagnoseService.getAll());
        model.addAttribute("medicalHistory_id", medicalHistory_id);

        return "/doctor/diagnoseResultController/diagnoseResult";
    }

    @GetMapping("/{medicalHistory}/diagnoseResults")
    public String getDiagnosisResults(Model model,Principal principal,HttpServletRequest uriBuilder, @PathVariable("medicalHistory") Long medicalHistory, Pageable pageable) {

        if (principal == null) {
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        var diagnoseResult = diagnoseResultService.getAll(pageable, (medicalHistory));
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(diagnoseResult, propertiesService.getDefaultPageSize(), model, uri);
        model.addAttribute("medicalHistory_id", medicalHistory);
        return "/doctor/diagnoseResultController/diagnoseResults";
    }

    @PostMapping
    public String createDirection(@Valid DiagnoseResultRegisterForm diagnoseResultRegisterForm, BindingResult validationResult,
                                  RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/diagnoseResult/"+diagnoseResultRegisterForm.getMedicalHistoryId()+"/diagnoseResult";
        }

        diagnoseResultService.createDiagnoseResult(diagnoseResultRegisterForm);

        return "redirect:/doctor/diagnoseResult/"+diagnoseResultRegisterForm.getMedicalHistoryId()+"/diagnoseResults";
    }

}
