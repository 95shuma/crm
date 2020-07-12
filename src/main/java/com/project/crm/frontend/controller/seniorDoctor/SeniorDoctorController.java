package com.project.crm.frontend.controller.seniorDoctor;

import com.project.crm.backend.services.PasswordResetTokenService;
import com.project.crm.backend.services.PropertiesService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.NewPasswordRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller("pkg seniorDoctor SeniorDoctorController")
@RequestMapping("/senior-doctor")
@AllArgsConstructor
public class SeniorDoctorController {

    private final PropertiesService propertiesService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserService userService;

    @GetMapping
    public String adminHCFPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);

        return "seniorDoctor/seniorDoctor";
    }

//    @GetMapping("/senior-doctor")
//    public String regAdminHospital(Model model, Principal principal) {
//
//        userService.checkUserPresence(model, principal);
//
//        return "regAdminHospital";
//    }
//
//    @PostMapping("/senior-doctor")
//    public String addAdminHospital(@Valid UserRegisterForm userRegisterForm,
//                                   BindingResult validationResult,
//                                   RedirectAttributes attributes){
//        if (validationResult.hasFieldErrors()) {
//            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
//            return "redirect:/senior-doctor";
//        }
//
//        userService.createUser(userRegisterForm);
//
//        return "redirect:/admin";
//    }

    @GetMapping("/users/passwords")
    public String userPassword(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal){
        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(userService.getAll(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "/seniorDoctor/passwordControl";
    }

    @GetMapping("/users/{user_inn}/password")
    public String userPasswordEdit(Model model, Pageable pageable, HttpServletRequest uriBuilder,
                                   Principal principal, @PathVariable("user_inn") Long inn){
        userService.checkUserPresence(model, principal);
        model.addAttribute("user", userService.getByInn(inn));
        model.addAttribute("token", passwordResetTokenService.createPasswordResetToken(inn));
        return "/seniorDoctor/passwordEdit";
    }

    @PostMapping("/users/password")
    public String createNewPassword(@Valid NewPasswordRegisterForm newPasswordRegisterForm, BindingResult validationResult,
                                    RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/senior-doctor/users/" + newPasswordRegisterForm.getUserInn() + "/password";
        }

        passwordResetTokenService.resetPassword(newPasswordRegisterForm);

        return "redirect:/senior-doctor";
    }

}
