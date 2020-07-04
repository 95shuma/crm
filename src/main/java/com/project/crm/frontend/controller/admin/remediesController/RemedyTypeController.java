package com.project.crm.frontend.controller.admin.remediesController;


import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.remediesService.RemediesFormService;
import com.project.crm.backend.services.remediesService.RemedyTypeService;
import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
import com.project.crm.frontend.forms.remediesForm.RemedyTypeRegisterForm;
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
@RequestMapping("/admin/remedyTypes")
@AllArgsConstructor
public class RemedyTypeController {

    private final UserService userService;
    private final RemedyTypeService remedyTypeService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getRemForms(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var names = remedyTypeService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(names, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/remedyController/remedyTypes";
    }

    @GetMapping("/remedyType")
    public String getRemForm(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("remedyTypes", remedyTypeService.getAll());

        return "admin/remedyController/remedyType";
    }

    @PostMapping
    public String createRemedyType(@Valid RemedyTypeRegisterForm remedyTypeRegisterForm, BindingResult validationResult,
                                   RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/remedyTypes/remedyType";
        }
        remedyTypeService.createRemedyType(remedyTypeRegisterForm);
        return "redirect:/admin/remedyTypes";
    }
}
