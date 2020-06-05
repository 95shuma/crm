package com.project.crm.frontend;

import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.*;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping
@AllArgsConstructor
public class RegisterController {

    private final PasswordEncoder encoder;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private RegistrationPlaceRepo registrationPlaceRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AdministratorRepo administratorRepo;

    //@Autowired RegionRepo regionRepo;

    @GetMapping("/doctorRegister")
    public String doctorRegisterPage(Model model){

        List<RegistrationPlace> registrationPlaces = registrationPlaceRepo.findAll();

        if (!model.containsAttribute("reg")) {
            model.addAttribute("reg", new PatientRegisterForm());
        }
        model.addAttribute("registrationPlaces", registrationPlaces);
        return "doctorRegister";
    }
    @GetMapping("/patientRegister")
    public String patientRegisterPage(Model model){

        List<Hospital> hospitals = hospitalRepo.findAll();
        List<RegistrationPlace> registrationPlaces = registrationPlaceRepo.findAll();

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

  /*  @GetMapping("/admin/reg-hospital")
    public String regHospital(Model model){
        model.addAttribute("regions",regionRepo.findAll());
        return "reg-hospital";
    }*/

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
                .registration_place_id(registrationPlaceRepo.findByName(doctorRegisterForm.getRegistration_place_id()))
                .build();

        doctorRepo.save(doctor);

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
                .hospital(hospitalRepo.findByName(patientRegisterForm.getHospital_id()))
                .role(roleRepo.findByName("пациент"))
                .registrationPlace(registrationPlaceRepo.findByName(patientRegisterForm.getRegistration_place_id()))
                .build();

        patientRepo.save(patient);

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

        Set<Role>roles = new HashSet<>();
        roles.add(roleRepo.findRoleById(Long.parseLong(adminHospitalRegisterForm.getRole_id())));

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
                .role(roleRepo.findRoleById(Long.parseLong(adminHospitalRegisterForm.getRole_id())))
                .build();

        administratorRepo.save(admin);

        return "redirect:/admin";
    }

  /*  @PostMapping("/admin/reg-hospital")
    public String addHospital(@RequestParam("name") String name, @RequestParam("region_id") Long region_id){
        Hospital hospital = Hospital.builder()
                .name(name)
                .region(regionRepo.findRegionById(region_id))
                .build();
        hospitalRepo.save(hospital);
        return "redirect:/admin";
    }*/

}
