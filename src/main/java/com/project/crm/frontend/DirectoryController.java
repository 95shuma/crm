package com.project.crm.frontend;

import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationType;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.RegistrationTypeRepo;
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
}
