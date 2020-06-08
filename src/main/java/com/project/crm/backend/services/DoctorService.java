package com.project.crm.backend.services;


import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.frontend.forms.DoctorRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .documentNumber(doctorRegisterForm.getDocumentNumber())
                .fullName(doctorRegisterForm.getFullName())
                .name(doctorRegisterForm.getName())
                .surname(doctorRegisterForm.getSurname())
                .middleName(doctorRegisterForm.getMiddleName())
                .birthDate(doctorRegisterForm.getBirthDate())
                .gender(doctorRegisterForm.getGender())
                .registrationPlace(registrationPlaceService.getByName(doctorRegisterForm.getRegistrationPlaceId()))
                .build();
        repo.save(doctor);

        var doctor_hospital = HospitalsDoctor.builder()
                .doctor(doctor)
                .role(roleService.getByName(doctorRegisterForm.getRoleId()))
                .hospital(hospitalService.getByName(doctorRegisterForm.getHospitalId()))
                .position(positionService.getByName(doctorRegisterForm.getPositionId()))
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
