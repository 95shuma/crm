package com.project.crm.frontend.controller;

import com.project.crm.backend.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class CatalogController {

    private final PositionService positionService;
    private final RegistrationTypeService registrationTypeService;
    private final RoleService roleService;
    private final RegistrationPlaceService registrationPlaceService;
    private final UserService userService;


    @GetMapping("/positions")
    public String positionPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("positions", positionService.getAll());
        return "positions";
    }
    @GetMapping("/registration_types")
    public String registrationTypePage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("registration_types", registrationTypeService.getAll());
        return "registrationTypes";
    }
    @GetMapping("/admin/create-role")
    public String rolePage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("roles",roleService.getAll());
        return "createRole";
    }
    @GetMapping("/admin/create-reg-place")
    public String placePage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("places",registrationPlaceService.getAll());
        return "createRegPlace";
    }

    @PostMapping("/position")
    public String addNewPosition(@RequestParam("name") String name){
        positionService.saveByName(name);
        return "redirect:/positions";
    }
    @PostMapping("/registration_type")
    public String addNewRegistration_type(@RequestParam("name") String name){
        registrationTypeService.saveByName(name);
        return "redirect:/registration_types";
    }
    @PostMapping("/admin/addRole")
    public String addRole(@RequestParam("name") String name){
        roleService.saveByName(name);
        return "redirect:/admin/create-role";
    }
    @PostMapping("/admin/addPlace")
    public String addPlace(@RequestParam("name") String name, @RequestParam String codePlace,
                           @RequestParam("groupCode") Integer groupCode){
        registrationPlaceService.save(name, codePlace, groupCode);
        return "redirect:/admin/create-reg-place";
    }
}
