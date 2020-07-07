package com.project.crm.frontend.controller.doctor;

import com.project.crm.backend.services.*;
import com.project.crm.backend.services.medicalHistoryService.MedicalHistoryService;
import com.project.crm.backend.services.medicalHistoryService.SickListService;
import com.project.crm.frontend.forms.medicalHistoryForms.SickListRegisterForm;
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
public class SickListController {

    private final UserService userService;
    private final MedicalHistoryService medicalHistoryService;
    private final PropertiesService propertiesService;
    private final SickListService sickListService;


    @GetMapping("/{id}/sickList")
    public String regRemedy(Model model, Principal principal, @PathVariable("id") Long medicalHistoryId) {
        userService.checkUserPresence(model, principal);
        model.addAttribute("medicalHistoryId",medicalHistoryId);
        return "doctor/sickListController/sickList";
    }

    @GetMapping("/{id}/sickLists")
    public String getLists(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal, @PathVariable("id") Long medicalHistoryId){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(sickListService.getAllSickLists(pageable, medicalHistoryId), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        model.addAttribute("medicalHistoryId", medicalHistoryId);
        return "doctor/sickListController/sickLists";
    }

    @PostMapping("/{id}/sickLists")
    public String createSickList(@Valid SickListRegisterForm sickListRegisterForm, BindingResult validationResult,
                               RedirectAttributes attributes,  @PathVariable("id") Long medicalHistoryId){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/medical-history/{id}/sickList";
        }
        sickListService.createSickList(sickListRegisterForm);
        return "redirect:/doctor/medical-history/{id}/sickLists";
    }

}
