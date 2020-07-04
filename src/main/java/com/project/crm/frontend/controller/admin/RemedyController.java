package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.*;
import com.project.crm.backend.services.remediesService.*;
import com.project.crm.frontend.forms.RemedyRegisterForm;
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

@Controller("pkg admin RemedyController")
@RequestMapping("/admin/remedies")
@AllArgsConstructor
public class RemedyController {

    private final UserService userService;
    private final PropertiesService propertiesService;
    private final DosageService dosageService;
    private final InternationalNameService internationalNameService;
    private final MeasureService measureService;
    private final PharmacologicalGroupService pharmacologicalGroupService;
    private final RemediesFormService remediesFormService;
    private final RemedyTypeService remedyTypeService;
    private final RemedyService remedyService;

    @GetMapping("/remedy")
    public String regRemedy(Model model, Principal principal, Pageable pageable) {
        userService.checkUserPresence(model, principal);
        model.addAttribute("remedyTypes",remedyTypeService.getAll(pageable));
        model.addAttribute("groups", pharmacologicalGroupService.getAll(pageable));
        model.addAttribute("internationalNames",internationalNameService.getAll(pageable));
        model.addAttribute("dosages", dosageService.getAllDosages(pageable));
        model.addAttribute("forms",remediesFormService.getAll(pageable));
        return "admin/remedyController/remedy";
    }

    @GetMapping
    public String getRemedies(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(remedyService.getAll(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "admin/remedyController/remedies";
    }


    @PostMapping
    public String createRemedy(@Valid RemedyRegisterForm remedyRegisterForm,
                                          BindingResult validationResult,
                                          RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/remedies/remedy";
        }
        remedyService.createRemedy(remedyRegisterForm);
        return "redirect:/admin/remedies";
    }
}