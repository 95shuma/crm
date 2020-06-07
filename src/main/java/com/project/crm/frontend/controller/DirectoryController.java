package com.project.crm.frontend.controller;

import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RegistrationPlaceService;
import com.project.crm.backend.services.RegistrationTypeService;
import com.project.crm.backend.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@AllArgsConstructor
public class DirectoryController {

    private final PositionService positionService;
    private final RegistrationTypeService registrationTypeService;
    private final RoleService roleService;
    private final RegistrationPlaceService registrationPlaceService;

    @GetMapping("/positions")
    public String positionPage(Model model){
        model.addAttribute("positions", positionService.getAll());
        return "positions";
    }
    @GetMapping("/registration_types")
    public String registrationTypePage(Model model){
        model.addAttribute("registration_types", registrationTypeService.getAll());
        return "registration_types";
    }
    @GetMapping("/admin/create-role")
    public String rolePage(Model model){
        model.addAttribute("roles",roleService.getAll());
        return "create-role";
    }
    @GetMapping("/admin/create-reg-place")
    public String placePage(Model model){
        model.addAttribute("places",registrationPlaceService.getAll());
        return "create-reg-place";
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
    public String addPlace(@RequestParam("name") String name, @RequestParam("code_place") String code_place,
                           @RequestParam("groupCode") Integer groupCode){
        registrationPlaceService.save(name, code_place, groupCode);
        return "redirect:/admin/create-reg-place";
    }
}
