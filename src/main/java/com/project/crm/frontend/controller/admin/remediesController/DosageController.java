package com.project.crm.frontend.controller.admin.remediesController;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.remediesService.DosageService;
import com.project.crm.backend.services.remediesService.MeasureService;
import com.project.crm.frontend.forms.HospitalRegisterForm;
import com.project.crm.frontend.forms.remediesForm.DosageRegisterForm;
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
@RequestMapping("/admin/dosages")
@AllArgsConstructor
public class DosageController {

    private final UserService userService;
    private final DosageService dosageService;
    private final MeasureService measureService;
    private final PropertiesService propertiesService;

    @GetMapping("/dosage")
    public String getDosage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("measures",measureService.getAll());

        return "admin/remedyController/dosage";
    }

    @GetMapping
    public String getDosages(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(dosageService.getAllDosages(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "admin/remedyController/dosages";
    }

    @PostMapping
    public String createDosage(@Valid DosageRegisterForm dosageRegisterForm, BindingResult validationResult,
                               RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/dosages/dosage";
        }

        dosageService.createDosage(dosageRegisterForm);

        return "redirect:/admin/dosages";
    }
}
