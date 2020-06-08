package com.project.crm.backend.services;


import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.repository.AdministratorRepo;
import com.project.crm.frontend.forms.AdminHospitalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdministratorService {

    private final AdministratorRepo repo;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public boolean existByInn(String inn){
        return repo.existsByInn(inn);
    }

    public void save(AdminHospitalRegisterForm adminHospitalRegisterForm){

        var admin = Administrator.builder()
                .inn(adminHospitalRegisterForm.getInn())
                .password(encoder.encode(adminHospitalRegisterForm.getPassword()))
                .documentNumber(adminHospitalRegisterForm.getDocumentNumber())
                .fullName(adminHospitalRegisterForm.getSurname()+" "+adminHospitalRegisterForm.getName()+" "+adminHospitalRegisterForm.getMiddleName())
                .name(adminHospitalRegisterForm.getName())
                .surname(adminHospitalRegisterForm.getSurname())
                .middleName(adminHospitalRegisterForm.getMiddleName())
                .birthDate(adminHospitalRegisterForm.getBirthDate())
                .gender(adminHospitalRegisterForm.getGender())
                .role(roleService.getById(Long.parseLong(adminHospitalRegisterForm.getRoleId())))
                .build();

        repo.save(admin);
    }
}
