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
import com.project.crm.frontend.forms.UserRegisterForm;
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

@RunWith(SpringRunner.class)            //Что-то вроде типа теста. В данном случае Интеграционный. От данного параметра зависит как поведут себя анотации @Autowired, @MockBean, @Mock внутри класса
@SpringBootTest                         //Запускает тест -> Формирует ApplicationContext. Более подробно в #91 тикете.
@AutoConfigureMockMvc                   //Конфигурирует mockMVC
public class DoctorControllerTest extends RepoMethods {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    //Если указать @Autowired то тест запустится напрямую с БД, т.е. при тестовом repo.save произойдет реальное сохранение в БД
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
    private String wrongInnSizeMore14;

    @Before
    public void setUp() {
        rn = new Random();
        faker = new Faker(new Locale("ru"));
        size = 5;
        innSeniorDoctor = "22222222222222";
        passwordSeniorDoctor = "22222222222222";
        //Correct
        correctInn = "12345678912345";
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
        wrongInnSizeMore14 = "11111111111111111111";
    }

    @After      //После каждого теста чистим репозитории
    public void tearDown() {
        registrationJournalRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        roleRepo.deleteAll();
        positionRepo.deleteAll();
    }

    //Этот метод добавляет необходимые для данного класса репозитории. В трех тестах используется, поэтому вынес в отдельный метод
    public void saveRepos() {
        saveRolesConstant(roleRepo);
        savePositionsConstant(positionRepo);
        saveHospitalsWith(size, hospitalRepo, placeRepo);

        User user = User.builder()
                .inn(Long.parseLong(innSeniorDoctor))
                .password(passwordSeniorDoctor)
                .documentNumber("ID6666667")
                .name(correctName)
                .surname(correctSurname)
                .middleName(correctMiddleName)
                .fullName(correctSurname + " " + correctName + " " + correctMiddleName)
                .gender(correctGender)
                .birthDate(today)
                .place(placeRepo.findAll().get(0))
                .build();
        userRepo.save(user);

        RegistrationJournal registrationJournal = RegistrationJournal.builder()
                .role(roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get())
                .user(user)
                .hospital(hospitalRepo.findAll().get(0))
                .position(positionRepo.findAll().get(0))
                .build();

        registrationJournalRepo.save(registrationJournal);
    }

    @Test   //Проверят успешный Get запрос.
    public void getDoctors_checkSuccessMethodAuthorizedBySeniorDoctor_expectGet_Authorized_View_FilledModels() throws Exception {
        saveRepos();

        this.mockMvc.perform(get("/senior-doctor/doctors/doctor")
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))                  //Эмитируем авторизованный запрос - Иначе будет redirect 302
        ).andExpect(status().is(200))                                                                                        //Ожидается успешный ответ 200
                .andExpect(view().name("seniorDoctor/doctorController/doctorRegister"))                    //Ожидается return на view
                .andExpect(model().attribute("user", UserDTO.from(userRepo.findByInn(Long.parseLong(innSeniorDoctor)).get())))     //Под кем авторизовались, должен отобразиться в model attribute - user
                .andExpect(model().attributeExists("reg"))                                                          //Или Создается пустой UserRegisterForm, либо уже должен быть - Проверяю на наличие.
                .andExpect(model().attribute("places", placeService.getAll()))                                        //Далее аналогично с User
                .andExpect(model().attribute("roles", roleService.getAll()))
                .andExpect(model().attribute("positions", positionService.getAll()))
                .andExpect(model().attribute(Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_SENIOR_DOCTOR))
                .andExpect(model().attribute(Constants.ROLE_DOCTOR, Constants.ROLE_DOCTOR))
                .andExpect(model().attribute(Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR));
    }

    @Test
    public void getDoctors_checkWrongMethodWithoutAuthorization_ExpectRedirect_Status302() throws Exception {
        mockMvc.perform(get("/senior-doctor/doctors/doctor")
        ).andExpect(status().is(302))
                .andExpect(redirectedUrl(Constants.URL_HTTP + Constants.URL_LOCALHOST + "/"));
    }

    //AddDoctor
    @Test
    public void createDoctor_checkSuccessMethod_shouldRedirectToViewAfterSave() throws Exception {
        saveRepos();

        mockMvc.perform(post("/senior-doctor/doctors/doctor")                                                //Эмитируем Post запрос на нужную страницу
                .with(csrf())                                                                                           //Добавляем token в параметы запроса. Иначе будет ошибка 403
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))              //Эмитируем авторизованный запрос - Иначе будет redirect 302
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", placeRepo.findAll().get(0).getId().toString()),
                        new BasicNameValuePair("positionId", positionRepo.findAll().get(0).getId().toString()),
                        new BasicNameValuePair("roleId", roleRepo.findByName(Constants.ROLE_PATIENT).get().getId().toString()),
                        new BasicNameValuePair("hospitalId", hospitalRepo.findAll().get(0).getId().toString()))))
                )
        ).andExpect(status().is(302))                                                                            //Если все прошло успешно, то додет до redirect на нужную страницу
                .andExpect(view().name("redirect:/senior-doctor"));                                    //Соответственно проверяем страницу
    }

    @Test       //Проверем что при Post запросе без токена выйдет ошибка 403
    public void createDoctor_checkWrongMethodWithoutCsrfToken_shouldRedirectTo403View() throws Exception {
        mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
        ).andExpect(status().is(403));
    }

    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithWrongInnSize_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", wrongInnSizeMore14),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(wrongInnSizeMore14, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Требуется ввести 14 цифр", fieldErrors.get(0).getDefaultMessage());
    }

    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithoutInn_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", ""),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals("", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Это поле не может быть пустым", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithWrongInnPattern_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", "ggasdfghjklhgf"),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals("ggasdfghjklhgf", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("ИНН состоит только из цифр начинается с 1 или 2 : ggasdfghjklhgf", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithExistInn_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", innSeniorDoctor),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(innSeniorDoctor, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот ИНН уже используется другим пользователем", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithoutPassword_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", ""),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("password", fieldErrors.get(0).getField());
        Assert.assertEquals("", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithWrongPasswordSize_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", "1234"),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("password", fieldErrors.get(0).getField());
        Assert.assertEquals("1234", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Пароль должен содержать минимум 8 символов", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithoutDocumentNumber_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", ""),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(2).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(2).getField());
        Assert.assertEquals("", fieldErrors.get(2).getRejectedValue());
        Assert.assertEquals("Обязательное поле", fieldErrors.get(2).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithWrongDocumentNumberSizeMax_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "ID123456789"),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals("ID123456789", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Требуется ввести 9 значений без пробела", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithWrongDocumentNumberSizeMin_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "ID12345"),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals("ID12345", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Требуется ввести 9 значений без пробела", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithWrongDocumentNumberPattern_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "123456789"),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals("123456789", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("№ докумета начинается с AN или ID и состоит из 7 цифр : 123456789", fieldErrors.get(0).getDefaultMessage());
    }
    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createDoctor_checkWrongMethodValidationErrorWithRedirectWithExistDocumentNumber_shouldReturnValidationErrorsForINNAndRedirectToView() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/doctors/doctor")
                .with(csrf())
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", "ID6666667"),
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
                .andExpect(view().name("redirect:/senior-doctor/doctors/doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals("ID6666667", fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот номер паспорта уже используется другим пользователем", fieldErrors.get(0).getDefaultMessage());
    }
}