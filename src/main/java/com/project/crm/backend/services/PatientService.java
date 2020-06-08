package com.project.crm.backend.services;


import com.project.crm.backend.model.Patient;
import com.project.crm.backend.repository.PatientRepo;
import com.project.crm.frontend.forms.PatientRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .documentNumber(patientRegisterForm.getDocumentNumber())
                .fullName(patientRegisterForm.getFullName())
                .name(patientRegisterForm.getName())
                .surname(patientRegisterForm.getSurname())
                .middleName(patientRegisterForm.getMiddleName())
                .birthDate(patientRegisterForm.getBirthDate())
                .gender(patientRegisterForm.getGender())
                .hospital(hospitalService.getByName(patientRegisterForm.getHospitalId()))
                .role(roleService.getByName("пациент"))
                .registrationPlace(registrationPlaceService.getByName(patientRegisterForm.getRegistrationPlaceId()))
                .build();

        repo.save(patient);
    }

    public Patient getByInn(String inn){
        return repo.findByInn(inn);
    }
}
