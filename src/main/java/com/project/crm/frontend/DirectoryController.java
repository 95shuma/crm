package com.project.crm.frontend;

import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.model.catalog.RegistrationType;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.RegistrationPlaceRepo;
import com.project.crm.backend.repository.RegistrationTypeRepo;
import com.project.crm.backend.repository.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PositionRepo positionRepo;

    @Autowired
    private RegistrationTypeRepo registrationTypeRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RegistrationPlaceRepo registrationPlaceRepo;

    @GetMapping("/positions")
    public String positionPage(Model model){
        model.addAttribute("positions", positionRepo.findAll());
        return "positions";
    }
    @GetMapping("/registration_types")
    public String registrationTypePage(Model model){
        model.addAttribute("registration_types", registrationTypeRepo.findAll());
        return "registration_types";
    }
    @GetMapping("/admin/create-role")
    public String rolePage(Model model){
        model.addAttribute("roles",roleRepo.findAll());
        return "create-role";
    }
    @GetMapping("/admin/create-reg-place")
    public String placePage(Model model){
        model.addAttribute("places",registrationPlaceRepo.findAll());
        return "create-reg-place";
    }

    @PostMapping("/position")
    public String addNewPosition(@RequestParam("name") String name){
        var position = Position.builder()
                .name(name)
                .build();
        positionRepo.save(position);
        return "redirect:/positions";
    }
    @PostMapping("/registration_type")
    public String addNewRegistration_type(@RequestParam("name") String name){
        var registration_type = RegistrationType.builder()
                .name(name)
                .build();
        registrationTypeRepo.save(registration_type);
        return "redirect:/registration_types";
    }
    @PostMapping("/admin/addRole")
    public String addRole(@RequestParam("name") String name){
        Role role = Role.builder()
                .name(name)
                .build();
        roleRepo.save(role);
        return "redirect:/admin/create-role";
    }
    @PostMapping("/admin/addPlace")
    public String addPlace(@RequestParam("name") String name, @RequestParam("code_place") String code_place){
        RegistrationPlace registrationPlace = RegistrationPlace.builder()
                .name(name)
                .code_place(code_place)
                .build();
        registrationPlaceRepo.save(registrationPlace);
        return "redirect:/admin/create-reg-place";
    }
}
