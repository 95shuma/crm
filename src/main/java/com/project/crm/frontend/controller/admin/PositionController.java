package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.PositionService;
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
public class PositionController {

    private final UserService userService;
    private final PositionService positionService;
    private final PropertiesService propertiesService;

    @GetMapping("/positions")
    public String getPositions(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){

        userService.checkUserPresence(model, principal);

        var positions = positionService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(positions, propertiesService.getDefaultPageSize(), model, uri);

        return "position";
    }

    @PostMapping("/positions/position")
    public String createPosition(@RequestParam("name") String name){
        positionService.createPosition(name);
        return "redirect:/admin/positions";
    }
}
