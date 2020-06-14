package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.PositionService;
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
@RequestMapping("/admin")
@AllArgsConstructor
public class PositionController {

    private final UserService userService;
    private final PositionService positionService;

    @GetMapping("/positions")
    public String getPositions(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        model.addAttribute("positions", positionService.getAll());
        return "position";
    }

    @PostMapping("/positions/position")
    public String createPosition(@RequestParam("name") String name){
        positionService.createPosition(name);
        return "redirect:/admin/positions";
    }
}
