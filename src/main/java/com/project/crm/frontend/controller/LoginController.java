package com.project.crm.frontend.controller;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
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

            if (registrationJournalService.existsByUserInnAndRoleId(inn, (long) 1)) {
                return "redirect:/admin";
            } else if (registrationJournalService.existsByUserInnAndRoleId(inn, (long) 2)){
                return "redirect:/senior-doctor";
            } else if (registrationJournalService.existsByUserInnAndRoleId(inn, (long) 3)){
                return "redirect:/doctor";
            } else if (registrationJournalService.existsByUserInnAndRoleId(inn, (long) 4)){
                return "redirect:/junior-doctor";
            } else if (registrationJournalService.existsByUserInnAndRoleId(inn, (long) 5)){
                return "redirect:/patient";
            }
        }
        return "/login";
    }
}
