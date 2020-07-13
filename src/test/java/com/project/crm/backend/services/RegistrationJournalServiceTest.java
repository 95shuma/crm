package com.project.crm.backend.services;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import java.util.Calendar;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationJournalServiceTest {
    @InjectMocks
    private RegistrationJournalService registrationJournalService;
    @Mock
    RoleRepo roleRepo;
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

    @Before
    public void setUp(){
        userRegisterForm = new UserRegisterForm();
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
    public void createRegistrationJournal_saveCorrectUser_expectSave() {
        when(roleRepo.findByName(Constants.ROLE_ADMIN)).thenReturn(Optional.of(Role.builder()
                .id((long) 1)
                .name(Constants.ROLE_ADMIN)
                .build()
        ));
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

        User testUser = User.builder()
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
                .build();

        Assertions.assertDoesNotThrow(() -> registrationJournalService.createRegistrationJournal(testUser, userRegisterForm));
    }
}