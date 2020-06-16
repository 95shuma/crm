package com.project.crm.frontend.controller.admin;

import com.project.crm.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String admin(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "admin/admin";
    }

}
