package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class PlaceController {

    private final UserService userService;
    private final PlaceService placeService;
    private final PropertiesService propertiesService;

    @GetMapping("/places")
    public String getPlaces(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var places = placeService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(places, propertiesService.getDefaultPageSize(), model, uri);

        return "admin/placeController/place";
    }


    @PostMapping("/places/place")
    public String createPlace(@RequestParam("name") String name, @RequestParam String codePlace,
                           @RequestParam("groupCode") Integer groupCode){
        placeService.createPlace(name, codePlace, groupCode);
        return "redirect:/admin/places";
    }
}
