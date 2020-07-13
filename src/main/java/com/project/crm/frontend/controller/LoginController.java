package com.project.crm.frontend.controller;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class LoginController {

    private final UserService userService;
    private final RegistrationJournalService registrationJournalService;
    private RoleService roleService;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error, Principal principal) {

        userService.checkUserPresence(model, principal);

        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/default")
    public String defaultPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model, Principal principal) {
        model.addAttribute("error", error);

        if(principal != null){
            Long inn = Long.parseLong(principal.getName());

            for (int i = 0; i < roleService.getAll().size(); i++){
                if (registrationJournalService.existsByUserInnAndRoleId(inn, roleService.getAll().get(i).getId())){
                    return Constants.REDIRECT_LIST().get(i);
                }
            }
        }
        return "/login";
    }
}
