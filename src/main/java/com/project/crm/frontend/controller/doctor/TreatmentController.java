package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.TreatmentRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/doctor/treatments")
@AllArgsConstructor
public class TreatmentController {

    private final UserService userService;
    private final TreatmentService treatmentService;
    private final RemedyService remedyService;
    private final ProcedureService procedureService;
    private final MedicalHistoryService medicalHistoryService;
    private final PropertiesService propertiesService;

    @GetMapping("/treatment")
    public String getTreatment(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        model.addAttribute("remedies",remedyService.getAll());
        model.addAttribute("procedures",procedureService.getAll());
        model.addAttribute("medicalHistories",medicalHistoryService.getAll());
        return "doctor/treatmentController/treatmentRegister";

    }

    @GetMapping
    public String getTreatments(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(treatmentService.getAll(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "doctor/treatmentController/treatments";
    }

    @PostMapping
    public String createTreatment(@Valid TreatmentRegisterForm treatmentRegisterForm, BindingResult validationResult,
                                  RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/treatments/treatment";
        }

        treatmentService.createTreatment(treatmentRegisterForm);

        return "redirect:/doctor";
    }

}
