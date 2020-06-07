package com.project.crm.backend.services;


import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.frontend.forms.DoctorRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepo repo;
    private final PasswordEncoder encoder;
    private final RegistrationPlaceService registrationPlaceService;
    private final HospitalsDoctorService hospitalsDoctorService;
    private final HospitalService hospitalService;
    private final RoleService roleService;
    private final PositionService positionService;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

    public void save(DoctorRegisterForm doctorRegisterForm){
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
        repo.save(doctor);

        var doctor_hospital = HospitalsDoctor.builder()
                .doctor(doctor)
                .role(roleService.getByName(doctorRegisterForm.getRole_id()))
                .hospital(hospitalService.getByName(doctorRegisterForm.getHospital_id()))
                .position(positionService.getByName(doctorRegisterForm.getPosition_id()))
                .build();

        hospitalsDoctorService.save(doctor_hospital);
    }

    public Doctor getByInn(String inn){
        return repo.findByInn(inn);
    }

    public Doctor getByName(String name){
        return repo.findByName(name);
    }
}
