package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.remediesService.ExaminationResultService;
import com.project.crm.backend.services.medicalHistoryService.InstrumExaminationService;
import com.project.crm.backend.services.medicalHistoryService.LabExaminationService;
import com.project.crm.frontend.forms.remediesForm.ExaminationResultForm;
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
@RequestMapping("/doctor/medical-history")
@AllArgsConstructor
public class ExaminationResultController {

    private final ExaminationResultService examinationResultService;
    private final UserService userService;
    private final PropertiesService propertiesService;
    private final InstrumExaminationService instrumExaminationService;
    private final LabExaminationService labExaminationService;

    @GetMapping("/{id}/examination-result")
    public String getExaminationResult(Model model, Principal principal, @PathVariable("id") Long medicalHistoryId){

        userService.checkUserPresence(model, principal);
        model.addAttribute("instrumExaminations",instrumExaminationService.getAll());
        model.addAttribute("labExaminations",labExaminationService.getAll());
        model.addAttribute("medicalHistoryId", medicalHistoryId);
        return "/doctor/examinationResult/examinationResult";

    }

    @GetMapping("/{id}/examination-results")
    public String getExaminationResults(Model model, Pageable pageable, HttpServletRequest uriBuilder,
                                        Principal principal, @PathVariable("id") Long medicalHistoryId){
        userService.checkUserPresence(model, principal);
        var examinationResults = examinationResultService.getAll(pageable, (medicalHistoryId));
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(examinationResults, propertiesService.getDefaultPageSize(), model, uri);
        model.addAttribute("medicalHistoryId", medicalHistoryId);
        return "/doctor/examinationResult/examinationResults";
    }

    @PostMapping("/{id}/examination-results")
    public String createExaminationResult(@Valid ExaminationResultForm examinationResultForm, BindingResult validationResult,
                                          RedirectAttributes attributes, @PathVariable("id") Long medicalHistoryId){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/medical-history/{id}/examination-result";
        }

        examinationResultService.createExaminationResult(examinationResultForm);

        return "redirect:/doctor/medical-history/{id}/examination-results";
    }
}
