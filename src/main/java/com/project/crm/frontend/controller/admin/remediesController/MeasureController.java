package com.project.crm.frontend.controller.admin.remediesController;


import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.remediesService.MeasureService;
import com.project.crm.frontend.forms.remediesForm.MeasureRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin/measures")
@AllArgsConstructor
public class MeasureController {

    private final UserService userService;
    private final MeasureService measureService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getMeasures(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var names = measureService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(names, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/remedyController/measures";
    }

    @GetMapping("/measure")
    public String getMeasure(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("measures", measureService.getAll());

        return "admin/remedyController/measure";
    }

    @PostMapping
    public String createMeasure(@Valid MeasureRegisterForm measureRegisterForm, BindingResult validationResult,
                                RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/measures/measure";
        }
        measureService.createMeasure(measureRegisterForm);
        return "redirect:/admin/measures";
    }
}
