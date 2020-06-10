package com.project.crm.backend.services;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

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

    public User getByInn(String inn){
        return userRepo.findByInn(inn).get();
    }

    public boolean existByInn(String inn){
        return userRepo.existsByInn(inn);
    }

    public void createUser(UserRegisterForm userRegisterForm){

        var user = User.builder()
                .inn(userRegisterForm.getInn())
                .password(encoder.encode(userRegisterForm.getPassword()))
                .documentNumber(userRegisterForm.getDocumentNumber())
                .fullName(userRegisterForm.getSurname()+" "+userRegisterForm.getName()+" "+userRegisterForm.getMiddleName())
                .name(userRegisterForm.getName())
                .surname(userRegisterForm.getSurname())
                .middleName(userRegisterForm.getMiddleName())
                .birthDate(userRegisterForm.getBirthDate())
                .gender(userRegisterForm.getGender())
                .place(placeRepo.findById((long) Integer.parseInt(userRegisterForm.getPlaceId())).get())
                .build();
        userRepo.save(user);

        registrationJournalService.createRegistrationJournal(user, userRegisterForm);
    }

    public void checkUserPresence(Model model, Principal principal){

        if(principal != null){

            String inn = principal.getName();

            if (registrationJournalRepo.existsByUserInnAndRoleId(inn, (long) 1)) {
                model.addAttribute("user", userRepo.findByInn(inn));
            } else if (registrationJournalRepo.existsByUserInnAndRoleId(inn, (long) 2)){
                model.addAttribute("user", userRepo.findByInn(inn));
            } else if (registrationJournalRepo.existsByUserInnAndRoleId(inn, (long) 3)){
                model.addAttribute("user", userRepo.findByInn(inn));
            } else if (registrationJournalRepo.existsByUserInnAndRoleId(inn, (long) 4)){
                model.addAttribute("user", userRepo.findByInn(inn));
            } else if (registrationJournalRepo.existsByUserInnAndRoleId(inn, (long) 5)){
                model.addAttribute("user", userRepo.findByInn(inn));
            }
        }
    }

}
