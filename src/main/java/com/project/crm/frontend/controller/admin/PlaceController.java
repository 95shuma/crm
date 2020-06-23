package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.dto.PlaceDTO;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
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
@RequestMapping("/admin/places")
@AllArgsConstructor
public class PlaceController {

    private final UserService userService;
    private final PlaceService placeService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String getPlaces(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var places = placeService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(places, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/placeController/places";
    }


    @PostMapping
    public String createPlace(@Valid PlaceDTO placeDTO, BindingResult validationResult,
                              RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/places/place";
        }

        placeService.createPlace(placeDTO);
        return "redirect:/admin/places";
    }
}
