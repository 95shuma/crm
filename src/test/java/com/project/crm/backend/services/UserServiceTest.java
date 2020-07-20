package com.project.crm.backend.services;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.PlaceRepo;
import com.project.crm.backend.repository.RegistrationJournalRepo;
import com.project.crm.backend.repository.RoleRepo;
import com.project.crm.backend.repository.UserRepo;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    PasswordEncoder encoder;
    @Mock
    PlaceRepo placeRepo;
    @Mock
    UserRepo userRepo;
    @Mock
    RoleRepo roleRepo;
    @Mock
    private RegistrationJournalService registrationJournalService;

    @Mock
    RegistrationJournalRepo registrationJournalRepo;

    private UserRegisterForm userRegisterForm;
    private Calendar calendar;
    private java.util.Date today;
    private String correctInn;
    private String correctPassword;
    private String correctDocumentNumber;
    private String correctName;
    private String correctSurname;
    private String correctMiddleName;
    private String correctGender;
    private String testString;

    private Model model;

    @Before
    public void setUp(){
        userRegisterForm = new UserRegisterForm();
        model = new ConcurrentModel();
        correctInn = "12345678912345";
        correctPassword = "12345678";
        correctDocumentNumber = "ID1234567";
        correctName = "Тест";
        correctSurname = "Тестов";
        correctMiddleName = "Тестович";
        correctGender = "мужской";
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        testString = "тест";
    }
    @Test
    public void createUser_saveCorrectUser_expectSave(){
        //Place
        when(placeRepo.findById((long) 1)).thenReturn(Optional.of(Place.builder()
                .id((long) 1)
                .name(testString)
                .codePlace((long) 11111111)
                .groupCode((long)1)
                .build())
        );
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Assertions.assertDoesNotThrow(() -> userService.createUser((userRegisterForm)));
    }
    @Test
    public void checkUserPresence_checkMethod_expectModelAttributeSameAsEnteredData(){
        List<Role> roleList = new ArrayList<>();
        for (int i = 1; i<=5; i++){
            roleList.add(Role.builder()
                    .id(Long.parseLong(Integer.toString(i)))
                    .name(testString)
                    .build()
            );
        }
        when(roleRepo.findAll()).thenReturn(roleList);
        when(registrationJournalRepo.existsByUserInnAndRoleId(Long.parseLong(correctInn), Long.parseLong("5"))).thenReturn(true);       //когда цикл доходит до нужной роли return true. Тем самым понятно что if срабатывает на той роли, на которой нужно
        when(userRepo.findByInn(Long.parseLong(correctInn))).thenReturn(Optional.of(User.builder()
                .id((long) 1)
                .inn(Long.parseLong(correctInn))
                .password(correctPassword)
                .documentNumber(correctDocumentNumber)
                .name(correctName)
                .surname(correctSurname)
                .middleName(correctMiddleName)
                .fullName(correctSurname+" "+correctName+" "+correctMiddleName)
                .gender(correctGender)
                .place(Place.builder()
                        .id((long) 1)
                        .name(testString)
                        .codePlace((long) 11111111)
                        .groupCode((long)1)
                        .build())
                .birthDate(today)
                .build())
        );
        Principal principal = () -> correctInn;

        userService.checkUserPresence(model, principal);            //checkUserPresence отрабатывает, model заполняется данными в зависимости от principal.
        Assertions.assertEquals(model.getAttribute("user") , userService.getByInn(Long.parseLong(correctInn)));
    }
}