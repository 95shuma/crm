package com.project.crm.frontend.controller.admin.remediesController;

import com.project.crm.backend.services.InternationalNameService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.InternationalNameRegisterForm;
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
@RequestMapping("/admin/internationalNames")
@AllArgsConstructor
public class InternationalNameController {

    private final UserService userService;
    private final InternationalNameService internationalNameService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getPositions(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var names = internationalNameService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(names, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/remedyController/internationalNames";
    }

    @GetMapping("/position")
    public String getPosition(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("internationalNames", internationalNameService.getAll());

        return "admin/remedyController/internationalName";
    }

    @PostMapping
    public String createInternationalName(@Valid InternationalNameRegisterForm internationalNameRegisterForm, BindingResult validationResult,
                                 RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/internationalNames/internationalName";
        }
        internationalNameService.createInternationalName(internationalNameRegisterForm);
        return "redirect:/admin/internationalNames";
    }
}
