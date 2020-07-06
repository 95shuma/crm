package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.SickListRegisterForm;
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

@Controller("pkg doctor SickListController")
@RequestMapping("/doctor/sickLists")
@AllArgsConstructor
public class SickListController {

    private final UserService userService;
    private final MedicalHistoryService medicalHistoryService;
    private final PropertiesService propertiesService;
    private final SickListService sickListService;

    @GetMapping("/sickList")
    public String regRemedy(Model model, Principal principal) {
        userService.checkUserPresence(model, principal);
        model.addAttribute("histories",medicalHistoryService.getAll());
        return "doctor/medicalHistoryController/sickList";
    }

    @GetMapping
    public String getLists(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(sickListService.getAllSickLists(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "doctor/medicalHistoryController/sickLists";
    }


    @PostMapping
    public String createSickList(@Valid SickListRegisterForm sickListRegisterForm,
                               BindingResult validationResult,
                               RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctor/sickLists/sickList";
        }
        sickListService.createSickList(sickListRegisterForm);
        return "redirect:/doctor/sickLists";
    }
}
