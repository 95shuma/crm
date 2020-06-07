package com.project.crm.backend.services;


import com.project.crm.backend.model.Patient;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.PatientRepo;
import com.project.crm.frontend.forms.PatientRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepo repo;
    private final PasswordEncoder encoder;
    private final RegistrationPlaceService registrationPlaceService;
    private final HospitalService hospitalService;
    private final RoleService roleService;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

    public void save(PatientRegisterForm patientRegisterForm){

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

        repo.save(patient);
    }

    public Patient getByInn(String inn){
        return repo.findByInn(inn);
    }
}
