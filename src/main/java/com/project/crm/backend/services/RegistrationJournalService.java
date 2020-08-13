package com.project.crm.backend.services;


import com.project.crm.backend.dto.RegistrationJournalDTO;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationJournalService {

    private final RegistrationJournalRepo registrationJournalRepo;
    private final WorkScheduleService workScheduleService;
    private final HospitalRepo hospitalRepo;
    private final RoleRepo roleRepo;
    private final PositionRepo positionRepo;

    public boolean existsByUserInnAndRoleId(Long inn, Long roleId){
        return registrationJournalRepo.existsByUserInnAndRoleId(inn, roleId);
    }
    public RegistrationJournalDTO findFirstByUserInnAndRole(Long inn, Long roleId){
        return  RegistrationJournalDTO.from(registrationJournalRepo.findFirstByUserInnAndRoleId(inn, roleId));
    }

    public Page<RegistrationJournalDTO> getAllSeniorDoctors(Pageable pageable){
        return registrationJournalRepo.findAllSeniorDoctors(pageable).map(RegistrationJournalDTO::from);
    }

    public Page<RegistrationJournalDTO> getAllHospitalUsers(Pageable pageable, Principal principal){
        RegistrationJournal registrationJournal = registrationJournalRepo.findByUserInn(Long.parseLong(principal.getName()));
        return registrationJournalRepo.findAllHospitalUsers(registrationJournal.getHospital().getId(), pageable).map(RegistrationJournalDTO::from);
    }

    public Page<RegistrationJournalDTO> getAllHospitalStaff(Pageable pageable, Principal principal){
        RegistrationJournal registrationJournal = registrationJournalRepo.findByUserInn(Long.parseLong(principal.getName()));
        return registrationJournalRepo.findAllHospitalStaff(registrationJournal.getHospital().getId(), pageable).map(RegistrationJournalDTO::from);
    }

    public Page<RegistrationJournalDTO> getAllHospitalPatients(Pageable pageable, Principal principal){
        RegistrationJournal registrationJournal = registrationJournalRepo.findByUserInn(Long.parseLong(principal.getName()));
        return registrationJournalRepo.findAllHospitalPatients(registrationJournal.getHospital().getId(), pageable).map(RegistrationJournalDTO::from);
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
    public List<RegistrationJournalDTO> getRegUsersByHospitalIdAndPositionIdAndWithoutSchedule(Long hospitalId, Long positionId){
        List<RegistrationJournalDTO> registrationJournalDTOList = RegistrationJournalDTO.listFrom(registrationJournalRepo.findByHospitalIdAndPositionId(hospitalId, positionId));
        List<RegistrationJournalDTO> finalRegUserDTOList = new ArrayList<>();
        registrationJournalDTOList.stream().forEach(registrationJournalDTO -> {
            if (workScheduleService.getWorkScheduleListByRegUserId(registrationJournalDTO.getId()).size() == 0)
            finalRegUserDTOList.add(registrationJournalDTO);
        });
        return finalRegUserDTOList;
    }

    public void createRegistrationJournal(User user, UserRegisterForm userRegisterForm){

        Long roleId = userRegisterForm.getRoleId();
        RegistrationJournal registrationJournal = null;

        if(roleId.equals(roleRepo.findByName(Constants.ROLE_ADMIN).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById(userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.ROLE_DOCTOR).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById(userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.ROLE_JUNIOR_DOCTOR).get().getId())){
            registrationJournal = RegistrationJournal.builder()
                    .hospital(hospitalRepo.findById(userRegisterForm.getHospitalId()).orElseGet(Hospital::new))
                    .user(user)
                    .role(roleRepo.findById(userRegisterForm.getRoleId()).orElseGet(Role::new))
                    .position(positionRepo.findById(userRegisterForm.getPositionId()).orElseGet(Position::new))
                    .build();
        }else if(roleId.equals(roleRepo.findByName(Constants.ROLE_PATIENT).get().getId())){
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
