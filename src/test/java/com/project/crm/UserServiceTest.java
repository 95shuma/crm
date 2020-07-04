package com.project.crm;

import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.repository.PlaceRepo;
import com.project.crm.backend.repository.UserRepo;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Calendar;
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
    private RegistrationJournalService registrationJournalService;


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

        userService.createUser(userRegisterForm);

    }
}