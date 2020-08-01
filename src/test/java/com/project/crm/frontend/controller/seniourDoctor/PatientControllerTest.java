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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.FieldError;

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
    private String wrongInnSizeMore14;
    private String wrongInnSizeLess14;
    private String emptyInn;
    private String wrongPassword;
    private String emptyPassword;
    private String emptyHospital;
    private String wrongDocumentNumber2;
    private String wrongName;
    private String emptyName;
    private String wrongSurname;
    private String emptySurname;
    private String wrongMiddleName;
    private String emptyMiddleName;
    private String emptyDate;
    private String wrongGender;


    @Before
    public void setUp(){
        rn = new Random();
        faker = new Faker(new Locale("ru"));
        size = 5;
        innSeniorDoctor = Constants.SENIOR_DOCTOR_INN;
        passwordSeniorDoctor =  Constants.SENIOR_DOCTOR_PASSWORD;
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
        wrongInnSizeMore14="12345678912345678";
        wrongInnSizeLess14="123456789";
        wrongPassword="abc";
        wrongDocumentNumber2="SK1234567";
        wrongName="111jkjkj";
        wrongSurname="1wer";
        wrongMiddleName="555jjjjj";
    }
    @After
    public void tearDown(){
        registrationJournalRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        roleRepo.deleteAll();
        placeRepo.deleteAll();
        positionRepo.deleteAll();
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
                .andExpect(redirectedUrl(Constants.URL_HTTP + Constants.URL_LOCALHOST + "/"));
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
                        new BasicNameValuePair("documentNumber", "ID2012328"),
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

        MvcResult mvcResult =   mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", wrongDocumentNumber),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongDocumentNumber, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Требуется ввести 9 значений без пробела", fieldErrors.get(0).getDefaultMessage());
    }


    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", wrongInnSizeMore14),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "ID2012328"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongInnSizeMore14, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Требуется ввести 14 цифр", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForINNless14AndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", wrongInnSizeLess14),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "ID2012328"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongInnSizeLess14, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Требуется ввести 14 цифр", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForEmptyINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", emptyInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "ID2012328"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(emptyInn, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForPasswordAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", "15358796314511"),
                        new BasicNameValuePair("password", wrongPassword),
                        new BasicNameValuePair("documentNumber", "ID2012328"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("password", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongPassword, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Пароль должен содержать минимум 8 символов", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForEmptyPasswordAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", "15358796314512"),
                        new BasicNameValuePair("password", emptyPassword),
                        new BasicNameValuePair("documentNumber", "ID2012338"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("password", fieldErrors.get(0).getField());
        Assert.assertEquals(emptyPassword, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForDocumentNumbAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", "15352696314512"),
                        new BasicNameValuePair("password", "17345678"),
                        new BasicNameValuePair("documentNumber", wrongDocumentNumber2),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongDocumentNumber2, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("№ докумета начинается с AN или ID и состоит из 7 цифр : SK1234567", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForWrongNameRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15358796794512"),
                        new BasicNameValuePair("password", "1297854796"),
                        new BasicNameValuePair("documentNumber", "ID1584794"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", wrongName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("name", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongName, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Имя должно содержать только буквы : 111jkjkj", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForEmptyNameRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15358795794512"),
                        new BasicNameValuePair("password", "1292854796"),
                        new BasicNameValuePair("documentNumber", "ID1584794"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", emptyName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("name", fieldErrors.get(0).getField());
        Assert.assertEquals(emptyName, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForWrongSurNameRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15358796794552"),
                        new BasicNameValuePair("password", "1297854796"),
                        new BasicNameValuePair("documentNumber", "ID1584594"),
                        new BasicNameValuePair("surname", wrongSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("surname", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongSurname, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Фамилия должна содержать только буквы : 1wer", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForEmptySurNameRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15348795794512"),
                        new BasicNameValuePair("password", "1292853796"),
                        new BasicNameValuePair("documentNumber", "ID1584784"),
                        new BasicNameValuePair("surname", emptySurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("surname", fieldErrors.get(0).getField());
        Assert.assertEquals(emptySurname, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForWrongMiddleNameRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15385796794552"),
                        new BasicNameValuePair("password", "1299954796"),
                        new BasicNameValuePair("documentNumber", "ID1584594"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", wrongMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("middleName", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongMiddleName, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Отчество должно содержать только буквы : 555jjjjj", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForEmptyMiddleNameRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15348795694512"),
                        new BasicNameValuePair("password", "1292853796"),
                        new BasicNameValuePair("documentNumber", "ID3584784"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", emptyMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("middleName", fieldErrors.get(0).getField());
        Assert.assertEquals(emptyMiddleName, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForDateRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15348795694512"),
                        new BasicNameValuePair("password", "1292853796"),
                        new BasicNameValuePair("documentNumber", "ID3584784"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", emptyDate),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("birthDate", fieldErrors.get(0).getField());
        Assert.assertEquals(emptyDate, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_checkWrongMethodValidationErrorWithRedirect_shouldReturnValidationErrorsForGenderRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "15348795654512"),
                        new BasicNameValuePair("password", "1292853796"),
                        new BasicNameValuePair("documentNumber", "ID3583784"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", wrongGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("gender", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongGender, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_expectInnExistError() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", innSeniorDoctor),
                        new BasicNameValuePair("password", "1292853796"),
                        new BasicNameValuePair("documentNumber", "ID2366666"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", "1"),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(innSeniorDoctor, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот ИНН уже используется другим пользователем", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createPatient_expectDocumentNumberExistError() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/patients/patient")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "12355555555555"),
                        new BasicNameValuePair("password", "1292853796"),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", "1"),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/senior-doctor/patients/patient"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("patientRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals(correctDocumentNumber, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот номер паспорта уже используется другим пользователем", fieldErrors.get(0).getDefaultMessage());
    }
}