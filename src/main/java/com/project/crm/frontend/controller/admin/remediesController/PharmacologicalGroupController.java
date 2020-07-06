package com.project.crm.frontend.controller.admin.remediesController;


import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.services.remediesService.PharmacologicalGroupService;
import com.project.crm.frontend.forms.remediesForm.PharmacologicalGroupRegisterForm;
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
@RequestMapping("/admin/groups")
@AllArgsConstructor
public class PharmacologicalGroupController {

    private final UserService userService;
    private final PharmacologicalGroupService pharmacologicalGroupService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getRemForms(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var names = pharmacologicalGroupService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(names, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/remedyController/groups";
    }

    @GetMapping("/group")
    public String getRemForm(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("groups", pharmacologicalGroupService.getAll());

        return "admin/remedyController/group";
    }

    @PostMapping
    public String createRemForm(@Valid PharmacologicalGroupRegisterForm pharmacologicalGroupRegisterForm, BindingResult validationResult,
                                RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/groups/group";
        }
        pharmacologicalGroupService.createPharmGroup(pharmacologicalGroupRegisterForm);
        return "redirect:/admin/groups";
    }
}
