package com.project.crm.backend.services;


import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationJournalService {

    private final RegistrationJournalRepo registrationJournalRepo;
    private final HospitalRepo hospitalRepo;
    private final RoleRepo roleRepo;
    private final PlaceRepo placeRepo;
    private final PositionRepo positionRepo;

    public boolean existsByUserInnAndRoleId(Long inn, Long roleId){
        return registrationJournalRepo.existsByUserInnAndRoleId(inn, roleId);
    }

    public List<RegistrationJournal> getAll(){
        return registrationJournalRepo.findAll();
    }

    public Long getRegJournalId(Long inn){
        return registrationJournalRepo.findByUserInn(inn).getId();
    }

    public List<RegistrationJournal> getDoctorsByHospitalId (Long hospitalId){
        return registrationJournalRepo.findByHospitalId(hospitalId);
    }

    public void createRegistrationJournal(User user, UserRegisterForm userRegisterForm){

        Long roleId = userRegisterForm.getRoleId();
        RegistrationJournal registrationJournal = null;

        if(roleId.equals(roleRepo.findByName(Constants.ADMIN).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.SENIOR_DOCTOR).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById(userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.DOCTOR).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById(userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.JUNIOR_DOCTOR).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById(userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.PATIENT).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .build();
        }

        assert registrationJournal != null;
        registrationJournalRepo.save(registrationJournal);
    }

}
