package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.PositionRegisterForm;
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
@RequestMapping("/admin/positions")
@AllArgsConstructor
public class PositionController {

    private final UserService userService;
    private final PositionService positionService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getPositions(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var positions = positionService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(positions, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/positionController/positions";
    }

    @GetMapping("/position")
    public String getPosition(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("positions", positionService.getAll());

        return "admin/positionController/position";
    }

    @PostMapping
    public String createPosition(@Valid PositionRegisterForm positionRegisterForm, BindingResult validationResult,
                                 RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/positions/position";
        }

        positionService.createPosition(positionRegisterForm);
        return "redirect:/admin/positions";
    }
}
