package com.project.crm.backend.services;


import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.model.catalog.Role;
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

    public void createRegistrationJournal(User user, UserRegisterForm userRegisterForm){

        Long roleId = userRegisterForm.getRoleId();
        RegistrationJournal registrationJournal = null;

        if(roleId == 1){
            registrationJournal = RegistrationJournal.builder()
                    .user(user)
                    .role(roleRepo.findById((long) userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .build();
        }else if(roleId == 2){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById((long) userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById((long) userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId == 3){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById((long) userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById((long) userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId == 4){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById((long) userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById((long) userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId == 5){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById((long) userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .build();
        }

        assert registrationJournal != null;
        registrationJournalRepo.save(registrationJournal);
    }

}
