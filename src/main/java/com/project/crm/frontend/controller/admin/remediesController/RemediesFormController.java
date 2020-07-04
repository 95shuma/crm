package com.project.crm.frontend.controller.admin.remediesController;


import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.remediesService.MeasureService;
import com.project.crm.backend.services.remediesService.RemediesFormService;
import com.project.crm.frontend.forms.remediesForm.MeasureRegisterForm;
import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
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
@RequestMapping("/admin/forms")
@AllArgsConstructor
public class RemediesFormController {

    private final UserService userService;
    private final RemediesFormService remediesFormService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getRemForms(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var names = remediesFormService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(names, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/remedyController/forms";
    }

    @GetMapping("/form")
    public String getRemForm(Model model, Principal principal, Pageable pageable){

        userService.checkUserPresence(model, principal);

        model.addAttribute("measures", remediesFormService.getAll(pageable));

        return "admin/remedyController/form";
    }

    @PostMapping
    public String createRemForm(@Valid RemediesFormRegisterForm remediesFormRegisterForm, BindingResult validationResult,
                                RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/forms/form";
        }
        remediesFormService.createRemediesForm(remediesFormRegisterForm);
        return "redirect:/admin/forms";
    }
}
