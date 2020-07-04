package com.project.crm;

import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

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