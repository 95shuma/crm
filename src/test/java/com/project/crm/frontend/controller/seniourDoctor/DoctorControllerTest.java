package com.project.crm.frontend.controller.seniourDoctor;

import com.project.crm.backend.model.User;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Calendar;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private PlaceService placeService;
    @MockBean
    private PositionService positionService;

    private UserRegisterForm userRegisterForm;
    private Calendar calendar;
    private java.util.Date today;
    private String innSeniorDoctor;
    private String passwordSeniorDoctor;
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
        innSeniorDoctor = "22222222222222";
        passwordSeniorDoctor = "22222222222222";
        correctInn = "12345678912345";
        correctPassword = "123456789";
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
    public void getDoctors_checkMethod_expect() throws Exception {
        this.mockMvc.perform(get("/senior-doctor/doctors/doctor")
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
        ).andExpect(status().isOk())
        .andExpect(view().name("seniorDoctor/doctorController/doctorRegister"))
        .andExpect(model().attribute("user", userService.getByInn(Long.parseLong(innSeniorDoctor))))
        //.andExpect(model().attribute("reg", new UserRegisterForm()))
        .andExpect(model().attribute("places", placeService.getAll()))
        .andExpect(model().attribute("roles", roleService.getAll()))
        .andExpect(model().attribute("positions", positionService.getAll()))
        .andExpect(model().attribute(Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_SENIOR_DOCTOR))
        .andExpect(model().attribute(Constants.ROLE_DOCTOR, Constants.ROLE_DOCTOR))
        .andExpect(model().attribute(Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR));
    }
}