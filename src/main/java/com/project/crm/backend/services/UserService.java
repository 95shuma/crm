package com.project.crm.backend.services;

import com.project.crm.backend.repository.AdministratorRepo;
import com.project.crm.backend.repository.HospitalsDoctorRepo;
import com.project.crm.backend.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserService {

    private final AdministratorService administratorService;
    private final HospitalsDoctorService hospitalsDoctorService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public void checkUserPresence(Model model, Principal principal){

        if(principal != null){

            String inn = principal.getName();
            if (administratorService.existByInn(inn)) {
                model.addAttribute("user", administratorService.getByInn(inn));
            } else if (hospitalsDoctorService.existByInnForAdminHCF(inn)){
                model.addAttribute("user", doctorService.getByInn(inn));
            } else if (hospitalsDoctorService.existByInnForDoctor(inn)){
                model.addAttribute("user", doctorService.getByInn(inn));
            } else {
                model.addAttribute("user", patientService.getByInn(inn));
            }
        }
    }

}
