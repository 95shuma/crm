package com.project.crm.frontend.controller.seniourDoctor;

import com.github.javafaker.Faker;
import com.project.crm.backend.dto.UserDTO;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.util.Constants;
import com.project.crm.backend.util.RepoMethods;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest extends RepoMethods {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PositionService positionService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PlaceRepo placeRepo;
    @Autowired
    RegistrationJournalRepo registrationJournalRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    HospitalRepo hospitalRepo;
    @Autowired
    PositionRepo positionRepo;

    private static Faker faker;
    private static Random rn;
    private int size;
    private Calendar calendar;
    private java.util.Date today;
    private String innSeniorDoctor;
    private String passwordSeniorDoctor;
    //Correct
    private String correctInn;
    private String correctPassword;
    private String correctDocumentNumber;
    private String correctName;
    private String correctSurname;
    private String correctMiddleName;
    private String correctGender;
    private String testString;
    //Wrong
    private String wrongDocumentNumber;

    @Before
    public void setUp(){
        rn = new Random();
        faker = new Faker(new Locale("ru"));
        size = 5;
        innSeniorDoctor = "12222222222222";
        passwordSeniorDoctor = "12222222222222";
        //Correct
        correctInn = "15555555555555";
        correctPassword = "123456789";
        correctDocumentNumber = "ID6666666";
        correctName = "Тест";
        correctSurname = "Тестов";
        correctMiddleName = "Тестович";
        correctGender = "мужской";
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        testString = "тест";
        //Wrong
        wrongDocumentNumber = "AN12345678";
    }
    @After
    public void tearDown(){
        registrationJournalRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        roleRepo.deleteAll();
    }
    public void saveRepos(){
        saveRolesConstant(roleRepo);
        saveHospitalsWith(size, hospitalRepo, placeRepo);

        User user = User.builder()
                .inn(Long.parseLong(innSeniorDoctor))
                .password(passwordSeniorDoctor)
                .documentNumber(correctDocumentNumber)
                .name(correctName)
                .surname(correctSurname)
                .middleName(correctMiddleName)
                .fullName(correctSurname+" "+correctName+" "+correctMiddleName)
                .gender(correctGender)
                .birthDate(today)
                .place(placeRepo.findAll().get(0))
                .build();
        userRepo.save(user);

        RegistrationJournal registrationJournal = RegistrationJournal.builder()
                .role(roleRepo.findByName(Constants.ROLE_PATIENT).get())
                .user(user)
                .hospital(hospitalRepo.findAll().get(0))
                .build();
        registrationJournalRepo.save(registrationJournal);
    }
    @Test
    public void getPatient_checkSuccessMethodAuthorizedBySeniorDoctor_expectGet_Authorized_View_FilledModels() throws Exception {
        saveRepos();

        this.mockMvc.perform(get("/senior-doctor/patients/patient")
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
        ).andExpect(status().isOk())
                .andExpect(view().name("seniorDoctor/patientController/patientRegister"))
                .andExpect(model().attribute("user", UserDTO.from(userRepo.findByInn(Long.parseLong(innSeniorDoctor)).get())))
                .andExpect(model().attributeExists("reg"))
                .andExpect(model().attribute("places", placeService.getAll()));
    }

    @Test
    public void getPatients_checkWrongMethodWithoutAuthorization_ExpectRedirect_Status302() throws Exception {
        mockMvc.perform(get("/senior-doctor/patients/patient")
        ).andExpect(status().is(302))
                .andExpect(redirectedUrl(Constants.URL_HTTP + Constants.URL_LOCALHOST + "/login"));
    }

    @Test
    public void createPatient_checkWrongMethodWithoutCsrfToken_shouldRedirectTo403View() throws Exception {
        mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
        ).andExpect(status().is(403));
    }

    //AddPATIENT
    @Test
    public void createPatient_checkSuccessMethod_shouldRedirectToViewAfterSave() throws Exception {
        saveRepos();
        mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", placeRepo.findAll().get(0).getId().toString()),
                        new BasicNameValuePair("roleId", roleRepo.findByName(Constants.ROLE_PATIENT).get().getId().toString()),
                        new BasicNameValuePair("hospitalId", hospitalRepo.findAll().get(0).getId().toString()))))
                )
        ).andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor"));
    }



    @Test
    public void createPatient_checkWrongMethodValidationError_shouldReturnValidationErrorsForDocNumbAndRedirectToView() throws Exception {
        saveRepos();
        mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", wrongDocumentNumber),
                        new BasicNameValuePair("surname", correctPassword),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        ).andExpect(status().is(302));
    }

}