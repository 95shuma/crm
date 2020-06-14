package com.project.crm.frontend.controller.juniorDoctor;

import com.project.crm.backend.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/junior-doctor")
@AllArgsConstructor
public class JuniorDoctor {

    private final UserService userService;

    @GetMapping
    public String juniorDoctor(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        return "juniorDoctor";
    }


}
