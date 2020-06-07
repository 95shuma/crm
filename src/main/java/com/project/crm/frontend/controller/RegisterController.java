package com.project.crm.frontend.controller;

import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.services.*;
import com.project.crm.frontend.forms.AdminHospitalRegisterForm;
import com.project.crm.frontend.forms.DoctorRegisterForm;
import com.project.crm.frontend.forms.PatientRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
@AllArgsConstructor
public class RegisterController {

    private final PasswordEncoder encoder;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final RegistrationPlaceService registrationPlaceService;
    private final HospitalService hospitalService;
    private final RoleService roleService;
    private final AdministratorService administratorService;

    @GetMapping("/doctorRegister")
    public String doctorRegisterPage(Model model){

        List<RegistrationPlace> registrationPlaces = registrationPlaceService.getAll();

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new DoctorRegisterForm());
        }
        model.addAttribute("registrationPlaces", registrationPlaces);
        return "doctorRegister";
    }

    @GetMapping("/patientRegister")
    public String patientRegisterPage(Model model){

        List<Hospital> hospitals = hospitalService.getAll();
        List<RegistrationPlace> registrationPlaces = registrationPlaceService.getAll();

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new PatientRegisterForm());
        }
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("registrationPlaces", registrationPlaces);
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
        List<RegistrationPlace> registrationPlaces = registrationPlaceService.getAll();
        model.addAttribute("places",registrationPlaces);
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

        var doctor = Doctor.builder()
                .inn(doctorRegisterForm.getInn())
                .password(encoder.encode(doctorRegisterForm.getPassword()))
                .document_number(doctorRegisterForm.getDocument_number())
                .full_name(doctorRegisterForm.getFull_name())
                .name(doctorRegisterForm.getName())
                .surname(doctorRegisterForm.getSurname())
                .middle_name(doctorRegisterForm.getMiddle_name())
                .birth_date(doctorRegisterForm.getBirth_date())
                .gender(doctorRegisterForm.getGender())
                .registration_place_id(registrationPlaceService.getByName(doctorRegisterForm.getRegistration_place_id()))
                .build();

        doctorService.save(doctor);

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

        var patient = Patient.builder()
                .inn(patientRegisterForm.getInn())
                .password(encoder.encode(patientRegisterForm.getPassword()))
                .document_number(patientRegisterForm.getDocument_number())
                .full_name(patientRegisterForm.getFull_name())
                .name(patientRegisterForm.getName())
                .surname(patientRegisterForm.getSurname())
                .middle_name(patientRegisterForm.getMiddle_name())
                .birth_date(patientRegisterForm.getBirth_date())
                .gender(patientRegisterForm.getGender())
                .hospital_id(hospitalService.getByName(patientRegisterForm.getHospital_id()))
                .role_id(roleService.getByName("пациент"))
                .registration_place_id(registrationPlaceService.getByName(patientRegisterForm.getRegistration_place_id()))
                .build();

        patientService.save(patient);

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

        var admin = Administrator.builder()
                .inn(adminHospitalRegisterForm.getInn())
                .password(encoder.encode(adminHospitalRegisterForm.getPassword()))
                .document_number(adminHospitalRegisterForm.getDocument_number())
                .full_name(adminHospitalRegisterForm.getSurname()+" "+adminHospitalRegisterForm.getName()+" "+adminHospitalRegisterForm.getMiddle_name())
                .name(adminHospitalRegisterForm.getName())
                .surname(adminHospitalRegisterForm.getSurname())
                .middle_name(adminHospitalRegisterForm.getMiddle_name())
                .birth_date(adminHospitalRegisterForm.getBirth_date())
                .gender(adminHospitalRegisterForm.getGender())
                .role(roleService.getById(Long.parseLong(adminHospitalRegisterForm.getRole_id())))
                .build();

        administratorService.save(admin);

        return "redirect:/admin";
    }

    @PostMapping("/admin/reg-hospital")
    public String addHospital(@RequestParam("name") String name, @RequestParam("place_id") Long place_id,
                              @RequestParam("street") String street, @RequestParam("house_num") String house_num){
        Hospital hospital = Hospital.builder()
                .name(name)
                .registrationPlace(registrationPlaceService.getById(place_id))
                .address(street+" "+house_num)
                .build();
        hospitalService.save(hospital);
        return "redirect:/admin";
    }

}
