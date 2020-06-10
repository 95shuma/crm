package com.project.crm.backend.services;


import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RegistrationJournal;
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

    public boolean existByInnForAdminHCF(String inn){
        boolean rtn = false;
        var registrationJournals = registrationJournalRepo.getAllByUserInn(inn);
        for (var hospitalUser : registrationJournals){
            if (hospitalUser.getRole().getName().equals(Constants.ADMIN_HCF)){
                rtn = true;
            }
        }
        return rtn;
    }

    public boolean existByInnForDoctor(String inn){
        boolean rtn = false;
        var registrationJournals = registrationJournalRepo.getAllByUserInn(inn);
        for (var registrationJournal : registrationJournals){
            if (registrationJournal.getRole().getName().equals(Constants.DOCTOR)){
                rtn = true;
            }
        }
        return rtn;
    }

    public List<RegistrationJournal> getAll(){
        return registrationJournalRepo.findAll();
    }

    public void createRegistrationJournal(User user, UserRegisterForm userRegisterForm){

        int roleId = Integer.parseInt(userRegisterForm.getRoleId());
        RegistrationJournal registrationJournal = null;

        if(roleId == 1){
            registrationJournal = RegistrationJournal.builder()
                    .user(user)
                    .role(roleRepo.findById((long) Integer.parseInt(userRegisterForm.getRoleId())).get())
                    .build();
        }else if(roleId == 2){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .user(user)
                    .role(roleRepo.findById((long) Integer.parseInt(userRegisterForm.getRoleId())).get())
                    .position(positionRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .build();
        }else if(roleId == 3){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .user(user)
                    .role(roleRepo.findById((long) Integer.parseInt(userRegisterForm.getRoleId())).get())
                    .position(positionRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .build();
        }else if(roleId == 4){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .user(user)
                    .role(roleRepo.findById((long) Integer.parseInt(userRegisterForm.getRoleId())).get())
                    .position(positionRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .build();
        }else if(roleId == 5){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById((long) Integer.parseInt(userRegisterForm.getHospitalId())).get())
                    .user(user)
                    .role(roleRepo.findById((long) Integer.parseInt(userRegisterForm.getRoleId())).get())
                    .build();
        }

        assert registrationJournal != null;
        registrationJournalRepo.save(registrationJournal);
    }

}
