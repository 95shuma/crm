package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.DirectionRepo;
import com.project.crm.backend.services.*;
import com.project.crm.backend.services.remediesService.InstrumExaminationService;
import com.project.crm.backend.services.remediesService.LabExaminationService;
import com.project.crm.frontend.forms.DirectionRegisterForm;
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
@RequestMapping("/doctor/directions")
@AllArgsConstructor
public class DirectionsController {

    private final UserService userService;
    private final DirectionService directionService;
    private final LabExaminationService labExaminationService;
    private final InstrumExaminationService instrumExaminationService;
    private final PositionService positionService;
    private final MedicalHistoryService medicalHistoryService;
    private final PropertiesService propertiesService;

    @GetMapping("/direction")
    public String getDirection(Model model, Principal principal){

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        model.addAttribute("labExamination",labExaminationService.getAll());
        model.addAttribute("instrumExamination",instrumExaminationService.getAll());
        model.addAttribute("position",positionService.getAll());
        model.addAttribute("medicalHistories",medicalHistoryService.getAll());
        return "doctor/directionController/directionRegister";

    }

    @GetMapping
    public String getDirections(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        if(principal == null){
            return "errorPage";
        }

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(directionService.getAll(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "doctor/directionController/directions";
    }

    @PostMapping
    public String createDirection(@Valid DirectionRegisterForm directionRegisterForm, BindingResult validationResult,
                                  RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/directions/direction";
        }

        directionService.createDirection(directionRegisterForm);

        return "redirect:/doctor";
    }

}
