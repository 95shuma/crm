package com.project.crm.backend.services;

import com.project.crm.backend.dto.UserDTO;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.PatientRegisterForm;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final HospitalRepo hospitalRepo;
    private final RoleRepo roleRepo;
    private final PlaceRepo placeRepo;
    private final PositionRepo positionRepo;
    private final RegistrationJournalRepo registrationJournalRepo;
    private final RegistrationJournalService registrationJournalService;
    private final PasswordEncoder encoder;

    public UserDTO getByInn(Long inn){
        User user = userRepo.findByInn(inn).get();
        if (user.getPlace() == null)
            return UserDTO.fromWithoutPlace(user);
        else
            return UserDTO.from(user);
    }
    public UserDTO getById(Long id){
        User user = userRepo.findById(id).get();
        if (user.getPlace() == null)
            return UserDTO.fromWithoutPlace(user);
        else
            return UserDTO.from(user);
    }

    public User getUserByInn(Long inn){
        return userRepo.findByInn(inn).get();
    }


    public boolean existByInn(Long inn){
        return userRepo.existsByInn(inn);
    }

    public List<User> getAll(){return userRepo.findAll();}

    public Page<UserDTO> getAll(Pageable pageable){
        return userRepo.findAll(pageable).map(UserDTO::from);
    }


    public Page<UserDTO> getAllPatients(Pageable pageable){
        return userRepo.findAllPatients(pageable).map(UserDTO::from);
    }

    public UserDTO getUserById(Long id){
        return UserDTO.from(userRepo.findById(id).get());
    }

    public Page<UserDTO> getAllDoctors(Pageable pageable){
        return userRepo.findAllHospitalStaff(pageable).map(UserDTO::from);
    }

    public Page<UserDTO> getAllSeniorDoctors(Pageable pageable){
        return userRepo.findAllSeniorDoctors(pageable).map(UserDTO::from);
    }

    public void createUser(UserRegisterForm userRegisterForm){

        var user = User.builder()
                .inn(Long.parseLong(userRegisterForm.getInn().trim()))
                .password(encoder.encode(userRegisterForm.getPassword()))
                .documentNumber(userRegisterForm.getDocumentNumber())
                .fullName(userRegisterForm.getSurname()+" "+userRegisterForm.getName()+" "+userRegisterForm.getMiddleName())
                .name(userRegisterForm.getName())
                .surname(userRegisterForm.getSurname())
                .middleName(userRegisterForm.getMiddleName())
                .birthDate(userRegisterForm.getBirthDate())
                .gender(userRegisterForm.getGender())
                .place(placeRepo.findById(userRegisterForm.getPlaceId()).get())
                .build();
        userRepo.save(user);

        registrationJournalService.createRegistrationJournal(user, userRegisterForm);
    }

    public void createUserFormPatientForm(PatientRegisterForm patientRegisterForm){
        UserRegisterForm userRegisterForm = new UserRegisterForm();

        userRegisterForm.setInn(patientRegisterForm.getInn());
        userRegisterForm.setPassword(patientRegisterForm.getPassword());
        userRegisterForm.setDocumentNumber(patientRegisterForm.getDocumentNumber());
        userRegisterForm.setName(patientRegisterForm.getName());
        userRegisterForm.setSurname(patientRegisterForm.getSurname());
        userRegisterForm.setMiddleName(patientRegisterForm.getMiddleName());
        userRegisterForm.setBirthDate(patientRegisterForm.getBirthDate());
        userRegisterForm.setGender(patientRegisterForm.getGender());
        userRegisterForm.setPlaceId(patientRegisterForm.getPlaceId());
        userRegisterForm.setHospitalId(patientRegisterForm.getHospitalId());
        userRegisterForm.setRoleId(patientRegisterForm.getRoleId());

        var user = User.builder()
                .inn(Long.parseLong(userRegisterForm.getInn()))
                .password(encoder.encode(userRegisterForm.getPassword()))
                .documentNumber(userRegisterForm.getDocumentNumber())
                .fullName(userRegisterForm.getSurname()+" "+userRegisterForm.getName()+" "+userRegisterForm.getMiddleName())
                .name(userRegisterForm.getName())
                .surname(userRegisterForm.getSurname())
                .middleName(userRegisterForm.getMiddleName())
                .birthDate(userRegisterForm.getBirthDate())
                .gender(userRegisterForm.getGender())
                .place(placeRepo.findById(userRegisterForm.getPlaceId()).get())
                .build();
        userRepo.save(user);

        registrationJournalService.createRegistrationJournal(user, userRegisterForm);
    }
    public void checkUserPresence(Model model, Principal principal){
        if(principal != null){
            Long inn = Long.parseLong(principal.getName());
            for (Role role: roleRepo.findAll()){
                if (registrationJournalRepo.existsByUserInnAndRoleId(inn, role.getId())){
                    model.addAttribute("user", getByInn(inn));
                    break;
                }
            }
        }
    }

}