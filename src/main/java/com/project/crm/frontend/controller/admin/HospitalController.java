package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admin/hospitals")
@AllArgsConstructor
public class HospitalController {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final PlaceService placeService;

    @GetMapping("/hospital")
    public String getHospital(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("places",placeService.getAll());

        return "hospital";
    }

    @PostMapping("/hospital")
    public String createHospital(@RequestParam String name, @RequestParam Long placeId,
                              @RequestParam String street, @RequestParam String houseNum){

        hospitalService.createHospital(name, placeService.getById(placeId), street+" "+houseNum);

        return "redirect:/admin";
    }
}
