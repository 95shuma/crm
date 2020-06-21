package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.HospitalRegisterForm;
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
@RequestMapping("/admin/hospitals")
@AllArgsConstructor
public class HospitalController {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final PlaceService placeService;
    private final PropertiesService propertiesService;

    @GetMapping("/hospital")
    public String getHospital(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("places",placeService.getAll());

        return "admin/hospitalController/hospital";
    }

    @GetMapping
    public String getHospitals(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(hospitalService.getAllHospitals(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "admin/hospitalController/hospitals";
    }

    @PostMapping
    public String createHospital(@Valid HospitalRegisterForm hospitalRegisterForm, BindingResult validationResult,
                                 RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/hospitals/hospital";
        }

        hospitalService.createHospital(hospitalRegisterForm);

        return "redirect:/admin";
    }
}
