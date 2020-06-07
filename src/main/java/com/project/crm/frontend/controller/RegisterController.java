package com.project.crm.frontend.controller;

import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.AdminHospitalRegisterForm;
import com.project.crm.frontend.forms.DoctorRegisterForm;
import com.project.crm.frontend.forms.PatientRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
@RequestMapping
@AllArgsConstructor
public class RegisterController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final RegistrationPlaceService registrationPlaceService;
    private final HospitalService hospitalService;
    private final RoleService roleService;
    private final AdministratorService administratorService;
    private final PositionService positionService;

    @GetMapping("/doctorRegister")
    public String doctorRegisterPage(Model model){

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new DoctorRegisterForm());
        }
        model.addAttribute("registrationPlaces", registrationPlaceService.getAll());
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("positions", positionService.getAll());
        return "doctorRegister";
    }

    @GetMapping("/patientRegister")
    public String patientRegisterPage(Model model){

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new PatientRegisterForm());
        }
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("registrationPlaces", registrationPlaceService.getAll());
        return "patientRegister";
    }
    @GetMapping("/adminHCF")
    public String adminHCFPage(Model model){
        return "adminHCF";
    }

    @GetMapping("/admin")
    public String getAdmin(){ return "admin-page";}

    @GetMapping("/admin/reg-admin-hospital")
    public String regAdminHospital() {return "reg-admin-hospital";}

    @GetMapping("/admin/reg-hospital")
    public String regHospital(Model model){
        model.addAttribute("places",registrationPlaceService.getAll());
        return "reg-hospital";
    }

    @PostMapping("/doctorRegister")
    public String doctorRegister(@Valid DoctorRegisterForm doctorRegisterForm,
                                  BindingResult validationResult,
                                  RedirectAttributes attributes){
        attributes.addFlashAttribute("reg", doctorRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/doctorRegister";
        }

        doctorService.save(doctorRegisterForm);

        return "redirect:/";
    }
    @PostMapping("/patientRegister")
    public String patientRegister(@Valid PatientRegisterForm patientRegisterForm,
                                 BindingResult validationResult,
                                 RedirectAttributes attributes){
        attributes.addFlashAttribute("reg", patientRegisterForm);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/patientRegister";
        }

        patientService.save(patientRegisterForm);

        return "redirect:/";
    }

    @PostMapping("/admin/reg-admin-hospital")
    public String addAdminHospital(@Valid AdminHospitalRegisterForm adminHospitalRegisterForm,
                                   BindingResult validationResult,
                                   RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/admin/reg-admin-hospital";
        }

        administratorService.save(adminHospitalRegisterForm);

        return "redirect:/admin";
    }

    @PostMapping("/admin/reg-hospital")
    public String addHospital(@RequestParam("name") String name, @RequestParam("place_id") Long place_id,
                              @RequestParam("street") String street, @RequestParam("house_num") String house_num){

        hospitalService.save(name, registrationPlaceService.getById(place_id), street+" "+house_num);

        return "redirect:/admin";
    }

}
